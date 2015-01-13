<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chart-2</title>
<jsp:include page="/pages/common-includes.jsp" />
<script type="text/javascript" src="../js/AllStudentOneExam.js"></script>
</head>
<body>
	<select name='selectopt' id='course_id'>
		<option value='course'>select course id</option>
	</select>

	<select name='selectopt' id='exam_wrap'>
		<option value='wrap'>select exam wrap</option>
	</select>
	<br />
	<button name="chart-button" class='btn' onclick='testMe()'>View Chart</button><br />
	<a id='backToChartMenu' href="chartSummary.jsp" style="margin-left: 20px; position: relative">Back to Chart Menu</a>
	<div id='no-data-display' class="list-group"></div>
	<div id="chart-range" data-height="260px" data-width="480px"
		style="margin-top: 20px; margin-left: 20px; position: relative; height: 400px; width: 700px;"
		class="jqplot-target"></div>
</body>
</html>