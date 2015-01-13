$(document).ready(function() {
	if (!readCookie('username')) {
		alert("you are not logged in");
		window.location.href = '/CTA/jsp/Login.jsp';
		return;
	}
	
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/CTA/ChartHelper?OPERATION_TYPE=getCourseData",
		success : fnDropdownDataFetched,
		error : fnDataFailure
	});
});

function fnDropdownDataFetched(response) {
	console.log(response);
	// response.data[0]
	var res_array = response.data.response.cdata;
	for ( var i = 0; i < res_array.length; i++) {
		var c_id = res_array[i].c_id;
		var dropdownText = res_array[i].course_id + "-"
				+ res_array[i].Course_sect_no;
		$('#course_id').append(
				"<option value='" + c_id + "'>" + dropdownText + "</option>");
	}

	$('#course_id').on('change', function() {
		var c_id = $('#course_id').val();
		$.ajax({
			type : "GET",
			dataType : "json",
			url : "/CTA/ChartHelper?OPERATION_TYPE=getExamWrapData&c_id=" + c_id,
			success : fnExamWrapFetched,
			error : fnDataFailure
		});
	});
}

function fnExamWrapFetched(response) {
	console.log(response);
	var res_array = response.data.response.edata;
	if (res_array.length == 0) {
		$('#exam_wrap').empty();
		$('#exam_wrap')
				.append("<option value='wrap'>select exam wrap</option>");
	}
	for ( var i = 0; i < res_array.length; i++) {
		var exam_wrap = res_array[i].exam_wrap;
		$('#exam_wrap').append(
				"<option value='" + exam_wrap + "'>" + exam_wrap + "</option>");
	}
}

function testMe() {
	var c_id = $('#course_id').val();
	var exam_wrap = $('#exam_wrap').val();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/CTA/Chart?OPERATION_TYPE=AllStudentOneExam&c_id=" + c_id + "&exam_wrap=" + exam_wrap,
		success : fnDataSuccess,
		error : fnDataFailure
	});
}

function fnDataSuccess(response) {
	var json = response.data.response.data;

	var quiz_array = [ [ 0, 0 ], [ 15, 0 ], [ 25, 0 ], [ 35, 0 ], [ 45, 0 ],
			[ 55, 0 ], [ 65, 0 ], [ 75, 0 ], [ 85, 0 ], [ 95, 0 ], [ 100, 0 ] ];

	for ( var j = 0; j < json.length; j++) {
		var exam_wrap = json[j].exam_wrap;
		var per = json[j].per;
		var count = json[j].count;
		var index = 0;
		if (per > 0 && per <= 10) {
			index = 0;
		} else if (per >= 11 && per <= 20) {
			index = 1;
		} else if (per >= 21 && per <= 30) {
			index = 2;
		} else if (per >= 31 && per <= 40) {
			index = 3;
		} else if (per >= 41 && per <= 50) {
			index = 4;
		} else if (per >= 51 && per <= 60) {
			index = 5;
		} else if (per >= 61 && per <= 70) {
			index = 6;
		} else if (per >= 71 && per <= 80) {
			index = 7;
		} else if (per >= 81 && per <= 90) {
			index = 8;
		} else if (per >= 91 && per <= 100) {
			index = 9;
		}

		var existing_count = quiz_array[index][1];
		quiz_array[index][1] = existing_count + count;
	}
	console.log(quiz_array);

	if(quiz_array.length == 0){
		$('#chart-range').empty();
		$('#no-data-display').append(
				"<li class='list-group-item'>No Data to Display</li>");
	}
	else{
	$('#chart-range').empty();
	$.jqplot('chart-range', [ quiz_array ], {
		title : 'Range of Marks in ' + $('#exam_wrap').val(),
		seriesDefaults : {
			rendererOptions : {
				smooth : true
			}
		},

		axes : {
			xaxis : {
				label : "Marks",
				min : '0',
				max : '100',
				tickInterval : '10',
				tickOptions : {
					formatString : '%d%d'
				},
				showTicks : true
			},
			yaxis : {
				label : "No of Students",
				min : '0',
				max : '100',
				tickOptions : {
					formatString : '%d%d'
				},
				tickInterval : '10',
				showTicks : true

			}
		},
		highlighter : {
			tooltipaxes : 'y',
			markerRenderer : new $.jqplot.MarkerRenderer({
				shadow : false
			}),
			show : true,
			sizeAdjust : 8,
			useAxesFormatters : true,
			tooltipAxes : 'x,y',
			fadeTooltip : true,
			tooltipFadeSpeed : "slow"
		}
	});
}
}

function fnDataFailure(response) {
	alert(response);
}

function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for ( var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0)
			return c.substring(nameEQ.length, c.length);
	}
	return null;
}

function eraseCookie(name) {
	createCookie(name, "", -1);
}