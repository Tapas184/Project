<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:form method="POST" action="postdcplanhome" modelAttribute="model">

		<table>
			<tr>
				<td>Customer id :<c:input path="id" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td>First name : <c:input path="fname" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td>Last name : <c:input path="lname" readonly="true" />
				</td>
			</tr>
			<tr>
				<td>Plan: <c:select path="plan">
						<c:option value="">-Select-</c:option>
						<c:options items="${rolelist}" />
					</c:select>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="Next"></td>
			</tr>
		</table>
	</c:form>
</body>
</html>