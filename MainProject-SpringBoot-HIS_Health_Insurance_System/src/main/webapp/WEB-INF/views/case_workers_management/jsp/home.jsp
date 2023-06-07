<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h2 style="text-align: center; color: blue;">Welcome to Case
		Workers And Admin Registration portal</h2>
	<br>
	<div style="color: green;">${loginSuccMsg}</div>
	<div style="text-align: right; color: green;">Login Name :${userName}</div>
	<br>
	 <div style="text-align: center;">
	    <a href="/admin$cw$edit/getalldetails">
	      <button>All CW And Admin List</button>
	    </a> 
	</div>
	<h2 style="text-align: center;">
		<a href="newregistration" ><button>New Registration</button></a>
	</h2>
</body>
</html>