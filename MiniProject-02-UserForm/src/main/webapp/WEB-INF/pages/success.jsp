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
		<frm:when test="${check}">
			<h1>
				<span style="color: green;">${successMsg}</span>
			</h1>
		</frm:when>
		<frm:otherwise>
			<span style="color: red;">${failuerMsg}</span>
			<br>
			<br>
			<a href="./" style="text-align: center;"><button>Home</button></a>
		</frm:otherwise>
	</frm:choose>
</body>
</html>