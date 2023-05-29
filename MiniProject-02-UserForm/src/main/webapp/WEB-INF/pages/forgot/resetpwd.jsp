<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reset Password</title>
</head>
<body>
	<frm:form method="POST" action="resetPwd" modelAttribute="frgt">
		<table style="margin-left: auto; margin-right: auto;">
			<tr>
				<td>Enter your Otp</td>
				<td><frm:input path="otp" /></td>
			</tr>
			<tr>
				<td>Enter New password</td>
				<td><frm:password path="newPass" /></td>
			</tr>
			<tr>
				<td>ReEnter password</td>
				<td><frm:password path="confPass" /></td>
			</tr>
			<tr>

				<td colspan="2"><input type="submit" value="Reset Password"></td>
			</tr>
		</table>
	</frm:form>
	<hr>
	<h5 align="center"><a href="./" ><button>Home</button></a></h5>
</body>
</html>