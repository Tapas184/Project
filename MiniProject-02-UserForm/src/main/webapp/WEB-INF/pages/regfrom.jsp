<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<head>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
</head>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<body>

	<h1>Registration Form</h1>
	<br>
	<h5>
		<font color="green"> ${msg} </font>
	</h5>
	<frm:form action="userReg" method="POST" modelAttribute="user">
		<table>
			<tr>
				<td>First Name :</td>
				<td><frm:input path="fname" title="Enter First Name" /></td>
			</tr>
			<tr>
				<td>Last Name :</td>
				<td><frm:input path="lname" title="Enter last name" /></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><frm:input path="email" title="Enter Email Address" /> 
						<span id="dupmail" style="color: red"></span>
				</td>
			</tr>
			<tr>
				<td>PhoneNo :</td>
				<td><frm:input path="phno" title="Enter Phone Number" /></td>
			</tr>
			<tr>
				<td>Gender :</td>
				<td><frm:radiobutton path="gender" value="Male" />Male &nbsp;
					<frm:radiobutton path="gender" value="Female" />Female</td>
			</tr>
			<tr>
				<td>Date Of Birth :</td>
				<td>
				<frm:input path="dob"/>
				</td>
			</tr>
			<tr>
				<td>Country :</td>
				<td><frm:select path="countryid">
						<frm:option value="">-Select-</frm:option>
						<frm:options items="${countryList}" />
					</frm:select></td>
			</tr>
			<tr>
				<td>State :</td>
				<td><frm:select path="stateid">
						<frm:option value="">-Select-</frm:option>
					</frm:select></td>
			</tr>
			<tr>
				<td>City :</td>
				<td><frm:select path="cityid">
						<frm:option value="">-Select-</frm:option>
					</frm:select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Register"></td>
				<td><input type="reset" value="Reset"></td>
			</tr>
		</table>

	</frm:form>
	<script type="text/javascript" src="./js/App.js"></script>
	  <script>
  $( function() {
    $( "#dob" ).datepicker();
  } );
  </script>
</body>

