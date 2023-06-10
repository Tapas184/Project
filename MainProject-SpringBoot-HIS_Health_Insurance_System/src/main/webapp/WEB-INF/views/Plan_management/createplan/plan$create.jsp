<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<html lang="text/html">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Create Plan</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : "yy-mm-dd",
			minDate : 0,
			changeMonth : true,
			changeYear : true
		});
		$("#datepicker2").datepicker({
			dateFormat : "yy-mm-dd",
			minDate : 0,
			changeMonth : true,
			changeYear : true

		});
	});
</script>
</head>
<body>
	<frm:form action="postcreate" method="POST" modelAttribute="pmodel">
	  <div style="color: green;text-align: center;">${resultmsg}</div>
		<table style="border: solid; margin: auto;">
			<caption>Plan Creation</caption>
			<tr>
				<th colspan="2" style="background-color: yellow; border: solid;">Plans</th>
			</tr>
			<tr>
				<td>Plans</td>
				<td><frm:input path="planName" placeholder="Plan Name" required="true"/>
				    <frm:hidden path="planId"/>
				</td>
			</tr>
			<tr>
				<td>Plan Description:</td>
				<td><frm:input path="planDescription" placeholder="Description" required="true" /></td>
			</tr>
			<tr>
				<td>Start Date:</td>
				<td><frm:input path="startDate" placeholder="yyyy-MM-dd"
						id="datepicker" required="true"/></td>
			</tr>
			<tr>
				<td>End Date:</td>
				<td><frm:input path="endDate" placeholder="yyyy-MM-dd"
						id="datepicker2" required="true"/></td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2"><input
					type="submit" value="Create Plan"
					style="background-color: rgba(0, 191, 255);" /></td>
			</tr>
		</table>
	</frm:form>
	<br>
	<div style="text-align: center;">
	   <a href="/registration/home"><button>Home</button></a>
	</div>
</body>
</html>