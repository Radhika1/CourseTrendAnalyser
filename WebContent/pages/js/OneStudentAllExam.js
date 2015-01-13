var exam_wrap_array = [];

$(document).ready(function() {
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
	for (var i = 0; i < res_array.length; i++) {
		var c_id = res_array[i].c_id;
		var dropdownText = res_array[i].course_id + "-"
				+ res_array[i].Course_sect_no;
		$('#course_id').append(
				"<option value='" + c_id + "'>" + dropdownText + "</option>");
	}

	$('#course_id')
			.on(
					'change',
					function() {
						var c_id = $('#course_id').val();
						$
								.ajax({
									type : "GET",
									dataType : "json",
									url : "/CTA/ChartHelper?OPERATION_TYPE=getExamWrapData&c_id="
											+ c_id,
									success : fnExamWrapFetched,
									error : fnDataFailure
								});
					});
}

function fnExamWrapFetched(response) {
	console.log(response);
	var exam_wrap_array = response.data.response.edata;
	if (exam_wrap_array.length == 0) {
		$('#exam_wrap').empty();
		$('#exam_wrap')
				.append("<option value='wrap'>select exam wrap</option>");
	}
	for (var i = 0; i < exam_wrap_array.length; i++) {
		var exam_wrap = exam_wrap_array[i].exam_wrap;
		$('#exam_wrap').append(
				"<option value='" + exam_wrap + "'>" + exam_wrap + "</option>");
	}
	localStorage.setItem("exam_wrap_array", JSON.stringify(exam_wrap_array));
}

function testMe() {
	var c_id = $('#course_id').val();
	var exam_wrap = $('#exam_wrap').val();
	var studID = $('#studID').val();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/CTA/Chart?OPERATION_TYPE=OneStudentAllExam&studID="
				+ studID + "&c_id=" + c_id,
		success : fnDataSuccess,
		error : fnDataFailure
	});
}

function fnDataSuccess(response) {
	var json = response.data.response.data;
	var exam_wrap_array_index = [];
	var exam_wrap_array_temp = JSON.parse(localStorage
			.getItem("exam_wrap_array"));
	for (var i = 0; i < exam_wrap_array_temp.length; i++) {
		exam_wrap_array_index[i] = i;
	}

	// [[[15,1,"q1",15],[42.5,2,42.5,50],[16,3,16,30]],[[35,2,35,50],[18,3,18,30]],[[24,3,24,30]]];

	var data_array = [];

	// for (var i = 0; i < json.length; i++) {
	// data_array_temp1 = [];
	// data_array_temp1.push(json[i].per);
	// for (var j = 0; j < exam_wrap_array_index.length; j++) {
	// if (json[i].exam_type.indexOf(exam_wrap_array_temp[j].exam_wrap) == 0) {
	// data_array_temp1.push(exam_wrap_array_index[j] + 1);
	// }
	// }
	// data_array_temp1.push(json[i].exam_type);
	// data_array_temp1.push(json[i].per);
	// data_array_temp2.push(data_array_temp1);
	//		
	// // data = [[a,b,0], [a,b,c], [a,c,b]]
	//
	// }

	// data= [[[1,2,3,4,5,6],pune],[[1,2,3,4,5,6],chennai]]

	for (var j = 0; j < exam_wrap_array_index.length; j++) {
		data_array_temp1 = [];
		data_array_temp2 = [];
		for (var i = 0; i < json.length; i++) {

			if (json[i].exam_type.indexOf(exam_wrap_array_temp[j].exam_wrap) == 0) {
				data_array_temp1.push(json[i].per);
			}
		}

		data_array_temp2.push(data_array_temp1);
		data_array_temp2.push(exam_wrap_array_temp[j].exam_wrap);
		data_array.push(data_array_temp2);
	}

	console.log(data_array_temp1);
	console.log(data_array_temp2);

	var series_ticks = [];

	for (var i = 0; i < exam_wrap_array_index.length; i++)
		series_ticks[i] = exam_wrap_array_index[i] + 1;

	var sdetailData = data_array;

	console.log("data_array : ");

	console.log(sdetailData);

	var colorArray = [ "#CC0000", "#FFCC66", "#CCCC99", "#CC6600", "#999999",
			"#FFA07A" ];
	if(data_array_temp1.length == 0){
		$('#chart-sdetail').empty();
		$('#no-data-display').append(
				"<li class='list-group-item'>No Data to Display</li>");
	}
	else{
		$('#no-data-display').append(
		"<li class='hidden'>No Data to Display</li>");
	$("#chart-sdetail").jqbargraph({
		data : sdetailData,
		colors : colorArray,
		barSpace : 13,
		width : 550,
		lines : true,
		lineInterval : 29,
		legends : series_ticks,
		legend : true,
		showValues : true,

	});
	$('.graphValue').hide();

	$('#chart-sdetail').find('div .graphBar').each(
			function() {
				$(this).click(
						function() {
							// $("#arrow-img-container").rotate({
							// angle : 0,
							// animateTo : 180,
							// duration : 2000
							// });
							var tick = $(this).parent().find(
									'.graph-label-text').text();
							fnShowChartDetails($('#studID').val(), tick, $(
									'#course_id').val());
							return;
							// fnShowDataTable(includeInactiveFlag, tick);
						});
			});

	function fnShowChartDetails(studentId, exam_wrap, cid) {
		$
				.ajax({
					type : "GET",
					dataType : "json",
					url : "/CTA/OneStudentAllExamHelperServlet?OPERATION_TYPE=OSAE_HELP&studId="
							+ studentId
							+ "&examWrap="
							+ exam_wrap
							+ "&cid="
							+ cid,
					success : fnGetData,
					error : fnDataFailure
				});

	}

	function fnGetData(data) {
		var responseData = data;
		console.log(responseData);

		var out = "<table class='table table-striped table-hover table-bordered table-condensed'><tr><th>Exam Type</th><th>Total Exam Weightage</th><th>Individual Exam Weightage</th><th>Total Marks</th><th>Marks Obtained</th></tr>";
		var json = responseData.OSAE_HELP.response.data;
		for (var i = 0; i < json.length; i++) {
			var exam_marks = json[i].exam_marks;
			var exam_total = json[i].exam_total;
			var individual_weightage = json[i].individual_weightage;
			var total_weightage = json[i].total_weightage;
			var exam_wrap = json[i].exam_wrap;
			out+="<tr><td>"+exam_wrap+"</td><td>"+total_weightage+"</td><td>"+individual_weightage+"</td><td>"+exam_total+"</td><td>"+exam_marks+"</td></tr>";
		}
		out+="</table>";

		// exam_marks: 85exam_total: 100exam_wrap:
		// "Project1"individual_weightage: 30total_weightage: 50

		$("#dialog").html(out);
		$("#dialog").dialog({
			position : [ 'top', 190 ],
			height : 300,
			width : 700,
			modal : true,
			show : 'fade',
			hide : 'drop',
			title : "Distribution of marks for student"
		});
	}

	// $('#chart-sdetail').bind(
	// 'jqplotDataHighlight',
	// function(ev, seriesIndex, pointIndex, data) {
	// $('#info2b').html(
	// 'series: ' + seriesIndex + ', point: ' + pointIndex
	// + ', data[2]: ' + data[2] + ', testX: '
	// + ev.pageX + ', testY: ' + ev.pageY);
	// });
	// $('#chart-sdetail').bind(
	// 'jqplotDataClick',
	// function(ev, seriesIndex, pointIndex, data) {
	// $('#info2c').html(
	// 'series: ' + seriesIndex + ', point: ' + pointIndex
	// + ', data: ' + data + ', testX: ' + ev.pageX
	// + ', testY: ' + ev.pageY);
	// });
	//
	// $('#chart-sdetail').bind('jqplotDataUnhighlight', function(ev) {
	// $('#info2b').html('Nothing');
	// });

}
}

function fnDataFailure(response) {
	alert(response);
}