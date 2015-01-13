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
<title>Insert title here</title>
</head>
<body>
<div align="right"> Welcome Professor <%=session.getAttribute("uname") %> </div>
<div align="right"><a href="Login.jsp"> LogOut </a></div> 
<div class="row">
      <div class="col-xs-6 col-md-3">
        <a href="gradingDetails.jsp" class="thumbnail">
          <img data-src="holder.js/100%x120" alt="100%x180" src="<%=request.getContextPath()%>/pages/images/enter.png" data-holder-rendered="true" style="height: 180px; width: 70%; display: block;">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <a href="FileUpload.jsp" class="thumbnail">
          <img data-src="holder.js/100%x180" alt="100%x120" src="<%=request.getContextPath()%>/pages/images/upload.png" data-holder-rendered="true" style="height: 180px; width: 70%; display: block;">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <a href="chartSummary.jsp" class="thumbnail">
          <img data-src="holder.js/100%x180" alt="100%x120" src="<%=request.getContextPath()%>/pages/images/graph.jpeg" data-holder-rendered="true" style="height: 180px; width: 70%; display: block;">
        </a>
      </div>
    </div>
</body>
</html>