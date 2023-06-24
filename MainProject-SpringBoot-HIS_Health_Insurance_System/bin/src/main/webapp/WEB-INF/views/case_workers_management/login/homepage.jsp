<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<html lang="txt/html">
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<frm:form action="postlogin" method="POST" modelAttribute="logindata">
	   <div style="color: red; text-align: center;">${errorMsg}</div>
		<table style="margin: auto; border: solid;">
		 <caption>Login Page</caption>
		  <tr>
		     <th colspan="2" style="text-align: center; background-color: yellow;">Login</th>
		  </tr>
			<tr>
				<td>User Id :</td>
				<td><frm:input path="emailid" placeholder="Username" /></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><frm:password path="pwd" placeholder="Password"
						showPassword="true" /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="submit" value="Login" /></td>
			</tr>
		</table>
	</frm:form>
	<hr>
	<table style="margin: auto;">
	<caption></caption>
	 <tr>
	<td style="text-align: left;">
	    <a href="/registration/newregistration">Don't have account?</a>&nbsp;&nbsp;
	 </td>
	 <td style="text-align: right;">
		<a href="forgotpass"><button>Forgot Password</button></a>
	</td>
	<tr>
	</table>

</body>
</html>