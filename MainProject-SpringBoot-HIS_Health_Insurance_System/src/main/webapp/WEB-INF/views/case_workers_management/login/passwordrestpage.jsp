<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html lang="text/html">
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Reset Password</title>
</head>
<body>
	<f:form action="postpasswordreset" method="POST" modelAttribute="frgt">
		<table style="border: solid; margin: auto;">
			<caption>Reset Password</caption>
			<tr>
				<th colspan="2" style="text-align: center; background-color: pink;">Password
					reset</th>
			</tr>
			<tr>
				<td style="background-color: yellow;">Email ID :</td>
				<td><f:input path="emailid" placeholder="Enter Mail" />
				     <div id="maildup" style="color: red; size: 1px;"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="submit" value="Reset"></td>
			</tr>
		</table>
	</f:form>
	<script src="../js/resetpass.js">
</script>
</body>
</html>