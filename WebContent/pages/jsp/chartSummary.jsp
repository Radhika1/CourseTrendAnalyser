<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/plugins/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/plugins/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap/bootstrap-theme.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap/bootstrap.min.css" />
<title>Charts Dashboard</title>
</head>
<body>

	<div class="list-group">
		    <a href="AllStudentOneExamChart.jsp" class="list-group-item">All Student One Exam</a> 
		    <a href="OneStudentAllExamChart.jsp" class="list-group-item">One Student All Exam</a>
			<a href="AllStudentAllExamChart.jsp" class="list-group-item">All Student All Exam</a>
			<a id='backToMainMenu' href="professorHome.jsp" class="list-group-item">Back to Main Menu</a>
	</div>

</body>
</html>