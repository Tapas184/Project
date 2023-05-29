<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h5 style="text-align: center">Login Account</h5>
	<f:form method="POST" action="login" modelAttribute="acc">
		<table style="margin-left: auto; margin-right: auto;">
			<tr>
				<td>Your Mail :</td>
				<td><f:input path="email" title="Enter User email" /></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><f:password path="password" title="Enter Your Password" /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="submit" value="SignIn" /></td>
			</tr>
		</table>
	</f:form>
	<hr>
	<h2 style="text-align: center;">
		<b>--OR--</b> <br> <a href="reg"><button>Create
				Account</button></a> &nbsp; &nbsp; <a href="forgotpwd"><button>Forgot
				Password</button></a>
	</h2>
</html>
