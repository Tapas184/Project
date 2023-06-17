<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:form method="POST" action="fetchApplication" modelAttribute="model">
		<table>
			<tr>
				<td>Search Id : <c:input path="id" placeholder="Application Id" />
					<input type="submit" value="Search">
				</td>
			</tr>
		</table>
	</c:form>
	<s:if test="${! empty check.id}">
			<table style="border: solid;">
			 <caption>Applicant Details</caption>
				<tr>
					<td>Applicant Id</td>
					<td>${model.id}</td>
				</tr>
				<tr>
					<td>Name</td>
					<td>${model.fname}&nbsp;${model.lname}</td>
				</tr>
				<tr>
					<td>Date Of Birth</td>
					<td>${model.dob}</td>
				</tr>
				<tr>
					<td>Gender</td>
					<td>${model.gender}</td>
				</tr>
				<tr>
					<td>SSN</td>
					<td>${model.ssn}</td>
				</tr>
				<tr>
					<td>Phone.No</td>
					<td>${model.phone}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>${model.mail}</td>
				</tr>
				<tr>
					<td>State</td>
					<td>${model.stateName}</td>
				</tr>
				<tr>
					<td colspan="2"><a href="/Dcplan/dcplanhome?id=${model.id}">Create Case</a></td>
				</tr>
			</table>
	</s:if>
</body>
</html>