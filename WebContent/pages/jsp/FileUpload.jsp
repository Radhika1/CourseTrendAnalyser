<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/plugins/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages//js/login.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/plugins/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/plugins/jquery-blink.js"></script>

<title>File Upload</title>
</head>
<body> 
        <div>
            <h3> Choose File to Upload in Server </h3>
            <form action="<%=request.getContextPath()%>/UploadServlet" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />
                <input type="submit" value="UploadServlet" />
            </form>          
        </div>
      
    </body>
</html>