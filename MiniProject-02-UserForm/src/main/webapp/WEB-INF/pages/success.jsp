<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success Message</title>
</head>
<body>
  <frm:choose>
     <frm:when test="${s==1}">
		<span style="color: green;">${successMsg}</span> 
	</frm:when>
	<frm:when test="${s==2}">
		<span style="color: red;">${failuerMsg}</span>
	</frm:when>
	<frm:when test="${s==3}">	 
		<span style="color: red;">${unlockMsg}</span> 
	</frm:when>	
	<frm:when test="${s==4}">
		<span style="color: red;">${wrongpass}</span> 
	</frm:when>	
		</frm:choose>
	<br> 
		<a href="./"><button>Home</button></a>
</body>
</html>