<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>
	<f:form method="POST" action="frgot" modelAttribute="frgt">
		<table>
			<tr>
				<td>Enter your Mail</td>
				<td><f:input path="email" title="Enter Email Address" />
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Send"></td>
			</tr>
		</table>

	</f:form>

</body>
</html>