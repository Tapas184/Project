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
<title>Unlock Account</title>
</head>
<body>
	<frm:form action="unlockAcc" method="POST" modelAttribute="acc">
		<font color="red"> <span id="errorid"></span>
		</font>
		<table>
			<tr>
				<td>Your Email :</td>
				<td>${email}</td>
			</tr>
			<tr>
				<td>Temporary Password :</td>
				<td><frm:input path="temPwd"  /></td>
			</tr>
			<tr>
				<td>New Password :</td>
				<td><frm:input path="newPwd" /></td>
			</tr>
			<tr>
				<td>Confirm Password :</td>
				<td><frm:input path="confrmPwd" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="unlock"
					onclick="return validatePwd()"></td>
			</tr>
		</table>
	</frm:form>
</body>
<script type="text/javascript" src="./js/App.js"></script>
</html>