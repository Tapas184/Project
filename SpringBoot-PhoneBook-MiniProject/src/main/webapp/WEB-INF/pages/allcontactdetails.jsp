<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" language="java"%>
<!DOCTYPE html>
<html lang="text/html">
<head>
<meta charset="ISO-8859-1">
<title>All Contact</title>
</head>
<body>
	<h1 style="text-align: center; color: blue">All Contact Details</h1>
	<c:choose>
		<c:when test="${!empty list }">
			<table
				style="margin-left: auto; margin-right: auto; background-color: pink;"
				border="1">
				<tr style="color: blue; text-align: center;">
					<th>Sl_No</th>
					<th>Name</th>
					<th>Email_Id</th>
					<th>Phone_No</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${list }" var="c" varStatus="index">
					<tr style="text-align: center;">
						<td>${index.count}</td>
						<td>${c.name }</td>
						<td>${c.email }</td>
						<td>${c.phoneNo }</td>
						<ins hidden="${c.cid}"></ins>
						<td>&nbsp;&nbsp;<a href="edit?cid=${c.cid}"><button>Edit</button></a>&nbsp;&nbsp;&nbsp;
							<a href="delete?cid=${c.cid}"
							onclick="return confirm('Are you sure for delete this name: ${c.name}')"><button>Delete</button></a></td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<h3 style="text-align: center;color: green;">${edditmsg}${deletemsg}</h3>
		</c:when>
		<c:otherwise>
			<h1 style="color: red; text-align: center;">Contact not found</h1>
		</c:otherwise>
	</c:choose>
	<hr>
	<h3 style="text-align: center;">
		<a href="./"><button>Home</button></a> &nbsp;&nbsp;&nbsp; <a
			href="addContact"><button>AddContact</button></a>
	</h3>
</body>
</html>