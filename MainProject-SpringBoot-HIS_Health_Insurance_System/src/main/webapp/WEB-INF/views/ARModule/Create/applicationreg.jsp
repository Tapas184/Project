<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Application Registration</title>
</head>
<body>

	<c:form action="postappform" method="POST" modelAttribute="model">
		<span style="color: green; margin: auto;">${validCitizen}</span>
		<table>
			<tr>
				<td>First Name:</td>
				<td><c:input path="fname" readonly="true" /></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><c:input path="lname" readonly="true" /></td>
			</tr>
			<tr>
				<td>Date Of Birth</td>
				<td><c:input path="dob" readonly="true" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><c:radiobutton path="gender" value="M" />Male&nbsp;&nbsp; 
				    <c:radiobutton path="gender" value="F" />Female
				</td>
			</tr>
			<tr>
				<td>SSN</td>
				<td><c:input path="ssn" readonly="true" /></td>
			</tr>
			<tr>
				<td>Ph.No</td>
				<td><c:input path="phone" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><c:input path="mail" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Create" /></td>
			</tr>
		</table>
	</c:form>
</body>
</html>