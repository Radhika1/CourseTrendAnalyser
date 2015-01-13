<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.sql.ResultSet" %>
<%@page import= "java.util.ArrayList" %>
<%@page import= "edu.utd.ooad.cta.domain.GradingDetailsBean"%>>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method = "post" name = "deleteform" action="<%=request.getContextPath()%>/DeleteController">
<div align="right"> Welcome Professor <%=session.getAttribute("uname") %> </div>
<div align="right"><a href="Login.jsp"> LogOut </a></div> 

<% ArrayList<GradingDetailsBean> rs = (ArrayList<GradingDetailsBean>)session.getAttribute("deleterows"); %>
<table>
<tr><th>Exam Type </th><th> Exam Individual Weightage</th> <th> Exam Total Weightage </th></tr>
<%for(GradingDetailsBean gdb : rs){ %>
<tr><td><%=gdb.getExamType() %> </td><td><%=gdb.getIndividualWeightage()%> </td><td><%=gdb.getTotalWeightage() %> </td></tr>
<% } %>
</table>
<br/>
<br/>
<br/>

<b> Do you wish to delete and insert new records? </b> <br/><br/>
<input type= "submit" id = "submit" name = "submit" value = "yes"  />&nbsp;&nbsp;&nbsp;  
<input type= "submit" id = "submit" name = "submit" value = "no"  />  
</form>
</body>
</html>