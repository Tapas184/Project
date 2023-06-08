<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Unlock Account</title>
</head>
<body>
	<frm:form action="postunlock" method="POST" modelAttribute="unlockpojo">
	   <span style="margin: auto;">${errorMsg}</span>
		<table style="margin: auto;">
		<caption>Unlock Account</caption>
			<tr>
				<td>Name :</td>
				<td>${fname}&nbsp; &nbsp;${lname}</td>
			</tr>
			<tr>
				<td>Email :</td>
				<td>${email}</td>
			</tr>
			<tr>
				<td>Temporary Password :</td>
				<td><frm:input path="tempass" placeholder="Temporary Pass" /></td>
			</tr>
			<tr>
				<td>New Password :</td>
				<td><frm:password path="newpassword" showPassword="true"
						placeholder="Eew password" /></td>
			</tr>
			<tr>
				<td>Confirm Password :</td>
				<td><frm:password path="confpassword" showPassword="true"
						placeholder="Re-Enter Password" /> <span id="error"
					style="color: red"></span></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Unlock"
					onclick="return unlock()"></td>
			</tr>
		</table>

	</frm:form>
	<script type="text/javascript" src="../js/app.js"></script>
</body>