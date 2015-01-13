<%-- <%@page import="java.util.ArrayList"%>
<%@page import = "java.sql.ResultSet" %> --%>
<%--  <%@page import="edu.utd.ooad.cta.bean.GradingDetailsBean"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!--

//-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/plugins/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/gradingDetailsUtil.js"> </script> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Trend Analyzer</title>
</head>
</head>
<body>
<div id= "gradDetailsDiv">
<form name="form" method="post" action="<%=request.getContextPath()%>/GradingDetailsController" onsubmit="return validateForm();">
<h1>Course Trend Analyzer</h1>
<h2>Grading Details</h2>

<select id='course_id'  name ="COURSENO">
	</select>

<%-- <% ResultSet rs = (ResultSet)session.getAttribute("courselist");%>
Course   : <select name ="COURSENO"> 
<% while (rs.next()) {%>
	<option value = <%=rs.getString(1) %>><%= rs.getString(1) %></option>
<%} %>
</select> --%>


<br><br>
<hr>
<h3> Details</h3><br>
<!-- Quiz details -->

<input type="checkbox" name = "quiz" id="quiz" onclick="gradDet.onChangeCheck('quiz', 'quizAdd', 'quizText', 'Quiz');">Quiz
<input type='text' size='2' id= "quizText" name="quizText" hidden = true/>
<input type="button" id="quizAdd" value="Add" hidden = true onclick="gradDet.add('Quiz','quizDiv');" />
  <div id="quizDiv"></div>
  <!-- Assignment details -->
<input type="checkbox" name = "assignment" id="assignment" onclick="gradDet.onChangeCheck('assignment', 'assignmentAdd', 'assignmentText', 'Assignment');">Assignment
<input type='text' size='2' id= "assignmentText" name = "assignmentText" hidden = true/>
<input type="button" id="assignmentAdd" value="Add" hidden = true onclick="gradDet.add('Assignment','assignmentDiv');" />
  <div id="assignmentDiv"></div>
  <!-- Project details -->
<input type="checkbox" name = "project" id="project" onclick="gradDet.onChangeCheck('project', 'projectAdd', 'projectText', 'Project');">Project
<input type='text' size='2' id= "projectText" name = "projectText" hidden = true/>
<input type="button" id="projectAdd" value="Add" hidden = true onclick="gradDet.add('Project','projectDiv');" />
  <div id="projectDiv"></div>
  <!-- Exam details -->
<input type="checkbox" name = "exam" id="exam" onclick="gradDet.onChangeCheck('exam', 'examAdd', 'examText', 'Exam');">Exam
<input type='text' size='2' id= "examText" name = "examText" hidden = true/>
<input type="button" id="examAdd" value="Add" hidden = true onclick="gradDet.add('Exam','examDiv');" />
  <div id="examDiv"></div>
 <!--  Others Details -->
 <input type="checkbox" name = "others" id="others" name ="others" onclick="gradDet.onChangeCheck('others', 'othersAdd', 'othersText', 'Others');">Others 
 <br/><input type ="text" id="othersId" hidden = true/>
 <input type='text' size='2' id= "othersText" hidden = true/>
<input type="button" id="othersAdd" value="Add" hidden = true onclick="gradDet.add('Others','othersDiv');" />
  <div id="othersDiv"></div><br/>
 <!--  Submit: On Submitting, changing all the elements into a json object with the following format ID: value -->
 <input type= "submit" id = "submit" value = "Submit" onclick= "gradDet.validate();"  />
 <!-- Reset: On Resetting clear all elements in the page -->
 <input type ="reset" id = "reset"/>
 <input type= "hidden" id = "quizCount" name = "quizCount" value = "0"/>
 <input type= "hidden" id = "projectCount" name = "projectCount" value = "0"/>
 <input type= "hidden" id = "assignmentCount" name = "assignmentCount" value = "0"/>
 <input type= "hidden" id = "examCount" name = "examCount" value = "0"/>
 <input type= "hidden" id = "othersCount" name = "othersCount" value = "0"/>
 
 <input type= "hidden" id = "quizarray" name = "quizarray" />
 <input type= "hidden" id = "projectarray" name = "projectarray" />
 <input type= "hidden" id = "assignmentarray" name = "assignmentarray" />
 <input type= "hidden" id = "examarray" name = "examarray" />

 </form>
 </div>
</body>
</html>