<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Role Page</title>
</head>
<body>
	<f:form action="create" method="POST" modelAttribute="roleM">
		<div style="color: red; text-align: center;" id="error">${resultMsg}</div>
		<div style="color: green; text-align: center;" id="success">${resultmsg}</div>
		<table style="margin: auto; border: solid;">
			<caption>
				<b>Role Registration Form</b>
			</caption>
			<tr>
				<th colspan="2" style="background-color: yellow;">Role Form</th>
			</tr>
			<tr>
				<td>Role Name :</td>
				<td><f:input path="role" placeholder="Role" required="true"/></td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2"><input
					type="submit" value="Create" /></td>
			</tr>
		</table>
	</f:form>
	<hr>
	<div style="text-align: center;">
	   <a href="/viewRoles/showRoles">
	   <button>Show All roles</button>
	   </a>
	</div>
	<script src="../js/rolecheck.js"></script>
</body>