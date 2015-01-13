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

<title>Login</title>

</head>
<body>

<form name="form" id="loginForm">

				<fieldset>
				<legend class="Regular"><font color="sky blue" size="4">Log In</font></legend>

				<table  width = "600" class="Regular" align ="center">
	
				<tr>
				<td><sup class="red">*</sup>User Name :</td> <td><input type = "text" maxlength = "35" size = 40 id="uname" name = "uname" class = "tb7" ></td>

				</tr>
				<tr><td></td></tr>
				<tr>
				<td><sup class="red">*</sup>Password :</td> <td><input type = "password" maxlength = "35" size = 40 id="passw" name = "passw" class = "tb7" ></td>
				</tr>
 
				<tr><td></td></tr>
				<tr>
				<td align="center"><input type="button" onclick="validateForm()"
							value="submit" class='btn' name="RetReg" class="loginButton"></td>
						<td align="center"><input type="reset" onclick="return reset()"
							value="Reset" class='btn' name = "Reset"></td>
				</tr></table>
				</fieldset>

			</form>
</body>
</html>