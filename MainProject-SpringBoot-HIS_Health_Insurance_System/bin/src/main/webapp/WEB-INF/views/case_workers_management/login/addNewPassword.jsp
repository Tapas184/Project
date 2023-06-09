<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html lang="text/html">
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
 <script
	src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<meta charset="ISO-8859-1">
<title>Create Password</title>
</head>
<body>
<f:form action="postAddNewPassword" method="POST" modelAttribute="model">
  <table style="border: solid; margin: auto;">
    <caption>Create Account Password</caption>
    <tr>
      <th colspan="2" style="border: solid;">Password Rest Form</th>
    </tr>
    <tr>
      <td>Email Id :</td>
      <td><f:input path="mail" readonly="true"/>
    </tr>
    <tr>
      <td>New Password :</td>
      <td><f:password path="newpassword" placeholder="New password" showPassword="true" />
    </tr>
    <tr>
      <td>Re-Enter Password :</td>
      <td><f:password path="confpassword" placeholder="Re-Enter password" showPassword="true" />
          <span id="error" style="color: red;"></span>
    </tr>
    <tr>
      <td><input type="submit" value="Submit" onclick="return confirm('Are you sure ?')"></td>
      </tr>
  </table>
</f:form>
<script type="text/javascript" src="../js/app.js"></script>
</body>
</html>