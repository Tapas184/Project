<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<frm:form action="postlogin" method="POST" modelAttribute="logindata">
	<div style="text-align: center; color: blue;">Login Form</div>
	<table style="margin: auto;">
		<tr>
			<td>User Id :</td>
			<td><frm:input path="emailid" placeholder="Username" /></td>
		</tr>
		<tr>
			<td>Password :</td>
			<td><frm:password path="pwd" placeholder="Password" showPassword="true"/></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;"><input type="submit"
				value="Login" /></td>
		</tr>
	</table>
	<hr>
	<div style="text-align: center;">
		<a href=""><button>Forgot Password</button></a>
	</div>
</frm:form>
<body>
</body>
</html>