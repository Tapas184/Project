<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact Registration</title>
</head>
<body>
	<h1
		style="color: blue; text-align: center; text-decoration: underline;">Contact
		Form Page</h1>
	<hr>
	<frm:form action="addcontact" method="post" modelAttribute="contact">
		<table
			style="background-color: pink; margin-left: auto; margin-right: auto; border: solid;">

			<tr style="background-color: yellow; text-align: center;">
				<td colspan="2" style="border: solid;">Contact</td>
			</tr>
			<tr>
			    <frm:hidden path="cid"/>
				<td><b>Name ::</b></td>
				<td><frm:input path="name" title="Enter Name" /></td>
			</tr>
			<tr>
				<td><b>Mail id ::</b></td>
				<td><frm:input path="email" title="Enter Email" /></td>
			</tr>
			<tr>
				<td><b>Phone Number ::</b></td>
				<td><frm:input path="phoneNo" title="Enter Phonenumber" /></td>
			</tr>
			<tr>
				<td style="text-align: center"><input type="submit"
					value="save"></td>
				<td style="text-align: center"><input type="reset"
					value="cancel"></td>
			</tr>
		</table>
	</frm:form>
	<h4 style="color: green; text-align: center;">
		<b>${msg}</b>
	</h4>
	<hr>
	<div style="text-align: center;">
		<a href="./"><button>Home</button></a>&nbsp;&nbsp;&nbsp;&nbsp;<a
			href="showAllContact"><button>ShowAllContact</button></a>
	</div>
</body>
</html>