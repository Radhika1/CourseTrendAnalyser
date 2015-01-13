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
		var c_id = $('#course_id').val();
	}}
	
function testMe() {
	var c_id = $('#course_id').val();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/CTA/Chart?OPERATION_TYPE=AllStudentAllExam&c_id=" + c_id,
		success : fnDataSuccess,
		error : fnDataFailure
	});
}


function fnDataSuccess(response) {
	var json = response.data.response.data;
	console.log(json)
	
	var ticksArray = []
	var dataArray = []
	
	for(var i=0;i<json.length;i++){
		ticksArray[i] = json[i].exam_wrap;
		dataArray[i] = json[i].average;
		
	}
	
	if(dataArray.length == 0){
		$('#chart-cdetail').empty();
		$('#no-data-display').append(
				"<li class='list-group-item'>No Data to Display</li>");
	}
	else{
	$('#chart-cdetail').empty();
	$.jqplot('chart-cdetail', [ dataArray ], {
		title : 'Range of Average Marks in All Exams',
		seriesDefaults : {
			rendererOptions : {
				smooth : false
			}
		},

		axes : {
			xaxis : {
				label : "Exam Type",
				renderer : $.jqplot.CategoryAxisRenderer,
				ticks : ticksArray,
				tickOptions : {
					formatString : '%d%d'
				},
				showTicks : true
			},
			yaxis : {
				label : "Marks",
				min : '0',
				max : '100',
				tickInterval : '10',
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
function fnDataFailure(response) {
	alert(response);
}