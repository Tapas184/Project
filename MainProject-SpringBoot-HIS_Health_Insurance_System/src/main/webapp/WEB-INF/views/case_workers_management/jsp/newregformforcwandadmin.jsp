<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
 <script
	src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
</head>
<body>
	<h1 style="text-align: center;">Admin and Case worker Registration
		Form</h1>
		<span style="text-align: center;">${resultmsg}</span>
	<frm:form action="postreg" method="POST" modelAttribute="emp">
		<table style="margin-left: auto; margin-right: auto;border: solid;">
		    <caption>Registration Form</caption>
		    <tr>
		      <th colspan="2" style="background-color:yellow ; text-align: center;">Registration</th>
		    </tr>
			<tr>
				<td>First Name :</td>
				<td><frm:input path="fname" placeholder="First Name" /></td>
			</tr>
			<tr>
				<td>Last Name :</td>
				<td><frm:input path="lname" placeholder="Last Name" /></td>
			</tr>
			<tr>
				<td>Email address :</td>
				<td><frm:input path="emailid" placeholder="Email Address" />
				<span id="dupmail" style="color: red"></span>
				</td>
			</tr>
			<tr>
				<td>Phone Number :</td>
				<td><frm:input path="phnumber" placeholder="Phone Number" /></td>
			</tr>
			<tr>
				<td>Gender :</td>
				<td><frm:radiobutton path="gender" value="M" />Male
					&nbsp;&nbsp;&nbsp; <frm:radiobutton path="gender" value="F" />Female
				</td>
			</tr>
			<tr>
				<td>Role :</td>
				<td><frm:select path="role">
						<frm:option value="">-Select-</frm:option>
						<frm:options items="${roleslist}" />
					</frm:select></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input type="submit" value="Registration">
				&nbsp;&nbsp; <input type="reset" value="Reset"></td>
			</tr>
		</table>
	</frm:form>
	<script src="../js/app.js">
</script>
</body>