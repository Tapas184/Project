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
     
		<span style="color: green;">${successMsg}</span> 
		<span style="color: red;">${failuerMsg}</span>
		<span style="color: red;">${unlockMsg}</span> 
		<span style="color: red;">${wrongpass}</span> 
		<span style="color: red;">${status}</span>
	<br> <br>
		<a href="./"><button>Home</button></a>
</body>
</html>