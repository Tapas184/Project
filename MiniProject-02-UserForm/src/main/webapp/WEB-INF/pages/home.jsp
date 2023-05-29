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
				<td><f:input path="username" title="Enter User Name" /></td>
			</tr>
			<tr>
				<td><f:input path="password" title="Enter Your Password" /></td>
			</tr>
			<tr>
				<td style="margin-right: auto;"><input type="submit" value="SignIn" /></td>
				<td style="margin-left: auto"><a href="resetpass"><button>Forgot
							Password</button></a>
			</tr>
		</table>
	</f:form>
	<hr>
	<h2 style="text-align: center;">
		<b>--OR--</b> <br> <a href="reg"><button>Create
				Account</button></a>
	</h2>
</html>
