<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="text/html">
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h2 style="text-align: center; color: blue;">Welcome Admin Registration portal</h2>
	<br>
	<div style="color: green;">${loginSuccMsg}</div>
	<div style="text-align: right; color: green;">Login Name
		:${userName}</div>
	<br>
	<h3 style="text-align: center;">
		<a href="/admin$cw$edit/getalldetails">
			<button>Show Admin&Cw List</button>
		</a>
	</h3>
	<h3 style="text-align: center;">
		<a href="newregistration"><button>Create Admin/Cw</button></a>
	</h3>
	<h3 style="text-align: center;">
		<a href="/role/home"><button>Create Roles</button></a>
	</h3>
	<h3 style="text-align: center;">
		<a href="/viewRoles/showRoles"><button>View Roles</button></a>
	</h3>
	<h3 style="text-align: center;">
		<a href="/plancreate/create"><button>Create Plan</button></a>
	</h3>
	<h3 style="text-align: center;">
		<a href="/planview/view"><button>View Plan</button></a>
	</h3>
	<h4 style="text-align: center;">
		<a href="/ar/ssncheck"><button>Application Registration</button></a>
	</h4>
		<h4 style="text-align: center;">
	   <a href="/arview/showApplications"><button>View Application</button></a>
	</h4>
	<h4 style="text-align: center;">
	   <a href="/arview/searchApp"><button>Search Application</button></a>
	</h4>
</body>
</html>