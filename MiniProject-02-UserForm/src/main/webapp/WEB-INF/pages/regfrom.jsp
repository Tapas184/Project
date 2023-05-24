<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
</head>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#email").blur(function() {
			$("#dupmail").html("");
			var emailId = $("#email").val();
			$.ajax({
				type : "GET",
				url : "validmail?email=" + emailId,
				success : function(res) {
					if (res == 'duplicate') {
						$("#dupmail").html("Email is already registered");
						$("#email").focus();
					} //if
				} //function
			}); //ajax
		});
	});

	$(document).ready(
			function() {
				$("#countryid").change(
						function() {
							$("#stateid").find('option').remove();
							$('<option>').val('').text('-Select-').appendTo(
									"#stateid");
							$("#cityid").find('option').remove();
							$('<option>').val('').text('-Select-').appendTo(
									"#cityid");
							var couId = $("#countryid").val();
							$.ajax({
								type : "GET",
								url : "getstate?cid=" + couId,
								success : function(data) {
									$.each(data,
											function(stateId, stateName) {
												$('<option>').val(stateId)
														.text(stateName)
														.appendTo("#stateid");
											});
								}
							});
						});
				$("#stateid").change(
						function() {
							$("#cityid").find('option').remove();
							$('<option>').val('').text('-Select-').appendTo(
									"#cityid");
							var stId = $("#stateid").val();
							$.ajax({
								type : "GET",
								url : "getcity?sid=" + stId,
								success : function(data) {
									$.each(data, function(cityId, cityName) {
										$('<option>').val(cityId)
												.text(cityName).appendTo(
														"#cityid");
									});
								}
							});
						});
			});
</script>
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
				<td><frm:input path="email" title="Enter Email Address" /> <font
					color="red">
						<div id="dupmail"></div>
				</font></td>
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
</body>
