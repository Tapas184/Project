<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="c" %>
<!DOCTYPE html>
<html lang="text/html">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:form action="postcreate" method="POST" modelAttribute="model">
    <div style="text-align: center; color: red;" >${invalidCitizen}</div>
    <div style="text-align: center; color:green;">${validCitizen}</div>
   <table>
      <tr>
        <td>First Name:</td>
        <td><c:input path="ssn" required="true" placeholder="Enter SSN"/></td>
      </tr>
      <tr>
        <td><input type="submit" value="Check" /></td>
      </tr>
   </table>
</c:form>
</body>
</html>