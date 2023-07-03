<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3 style="color: green;">Form submit wait for 24 hours we will update vai mail </h3>
<f:choose>
  <f:when test="${userRole.equalsIgnoreCase('admin')}">
    <h1><a href="/registration/adminhome">Home</a></h1>
  </f:when>
  <f:when test="${userRole.equalsIgnoreCase('cw')}">
    <h1><a href="/registration/cwhome">Home</a></h1>
  </f:when>
</f:choose>
</body>
</html>