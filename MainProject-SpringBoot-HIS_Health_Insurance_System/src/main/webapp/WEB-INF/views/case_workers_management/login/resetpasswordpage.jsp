<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html lang="text/html">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <f:form action="postOTPEntered" method="POST" modelAttribute="mdl">
     <table style="margin: auto; border: solid;">
       <caption>Rest Password Form</caption>
        <tr>
          <th colspan="2" style="background-color: yellow;text-align: center;">Reset Password</th>
        </tr>
        <tr>
          <td style="background-color: yellow;text-align: center;">Email otp :</td>
          <td><f:input path="tempass" placeholder="Email Password"/>
             <span style="color: green;">otp sent to:${tempasssentmail}</span>
          </td>
        </tr>
        <tr>
          <td style="background-color: yellow;text-align: center;">Phone OTP :</td>
          <td><f:input path="otp" placeholder="OTP"/>
              <span style="color: green;">otp set to:${phno}</span>
          </td>
        </tr>
        <tr>
          <td style="text-align: center;"><input type="submit" value="Reset">&nbsp;&nbsp;
              <input type="reset" value="Cancel">
          </td>
        </tr>
     </table>
  </f:form>
</body>
</html>