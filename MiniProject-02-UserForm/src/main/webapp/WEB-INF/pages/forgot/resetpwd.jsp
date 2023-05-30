<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
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
				<td><frm:password path="confPass" />
				<span id="error" style="color: red"></span>
				</td>
			</tr>
			<tr>

				<td colspan="2"><input type="submit" value="Reset Password" onclick="return conformPwd()"></td>
			</tr>
		</table>
	</frm:form>
	<script type="text/javascript" src="./js/App.js"></script>
	<hr>
	<h5 align="center"><a href="./" ><button>Home</button></a></h5>
	
</body>

</html>