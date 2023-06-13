<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="text/html">
<head>
<meta charset="ISO-8859-1">
<title>Case Worker Home</title>
</head>
<body>
	<h2 style="text-align: center; color: blue;">Welcome to Case
		Workers portal</h2>
	<br>
	<div style="color: green;">${loginSuccMsg}</div>
	<div style="text-align: right; color: green;">Login Name
		:${userName}</div>
	<br>
	<h4 style="text-align: center;">
	   <a href="/ar/ssncheck"><button>Application Registration</button></a>
	</h4>
</body>
</html>