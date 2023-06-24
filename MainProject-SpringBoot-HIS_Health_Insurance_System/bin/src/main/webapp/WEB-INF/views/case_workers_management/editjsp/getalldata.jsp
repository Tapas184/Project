<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" language="java"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="t"%>
<!DOCTYPE html>
<html lang="text/html">
<head>
<meta charset="ISO-8859-1">
<title>All Contact list</title>
</head>
<body>
	<h1 style="color: gray; text-align: center;">All Admin and Case
		Worker details</h1>
	<t:choose>
		<t:when test="${!empty page}">
			<table 
			style="margin-left: auto; margin-right: auto; background-color: pink;"
				border="1">
				<caption>All List</caption>
				<tr style="background-color: yellow; text-align: center;">
					<th>Sl_No</th>
					<th>Name</th>
					<th>EmailId</th>
					<th>PHNumber</th>
					<th>Role</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			<t:forEach items="${page.getContent()}" var="c" varStatus="Index">
			   <tr>
					<td>${Index.count}</td>
					<td>${c.fname}&nbsp;${c.lname}</td>
					<td>${c.emailid}</td>
					<td>${c.phnumber}</td>
					<td>${c.role}</td>
					<td>${c.status}</td>
					<ins hidden="${c.userid}"></ins>
				<t:choose>
					<t:when test="${c.status == 'LOCKED'|| c.status == 'LOCK'|| c.status == 'lock' || c.status == 'locked' || c.status == 'Locked'}">
						<td><a href="edit?id=${c.userid}"><button>Edit</button></a>&nbsp;&nbsp; 
						    <a href="unlock?id=${c.userid}"><button>Unlock</button></a>
						</td>
					</t:when>
					<t:when test="${c.status == 'INACTIVE'|| c.status == 'inactive' || c.status == 'Inactive'}">
						<td><a href="edit?id=${c.userid}"><button>Edit</button></a>&nbsp;&nbsp;
						    <a href="active?id=${c.userid}"><button>Active</button></a>
						</td>
					</t:when>
					<t:otherwise>
						<td><a href="edit?id=${c.userid}"><button>Edit</button></a>&nbsp;&nbsp;
						    <a href="delete?id=${c.userid}" onclick="return confirm('Are you sure want to Inactive this Id :${c.userid}')"><button>Delete</button></a>
						</td>
					</t:otherwise>
				</t:choose>	
			</tr>
			</t:forEach>
			</table>
		</t:when>
		<t:otherwise>
		   <h1 style="color: red;">List is empty</h1>
		</t:otherwise>
	</t:choose>
	<div style="color: green; text-align: center;">${editResult}</div>
	<div style="color: green; text-align: center;">${unlocksuccessmsg}</div>
	<div style="color: green; text-align: center;">${unlockerrormsg}</div>
	<div style="color: green; text-align: center;">${deleteAccountMsg}</div>
	<div style="color: green; text-align: center;">${activeAccountMsg}</div>
	<hr>
	<p style="text-align: center;">
	<t:if test="${!page.isFirst()}">
		<a href="getalldetails?page=page-1"><button>Prev</button></a>&nbsp;
	</t:if>
	<t:forEach var="i" begin="1" end="${page.getTotalPages()}" step="1">
          <a href="getalldetails?page=${i-1}"><button style="size:1mm;">${i}</button></a>&nbsp;&nbsp;
    </t:forEach>
	<t:choose>
		<t:when test="${!page.isLast()}">
			<a href="getalldetails?page=${page.getTotalPages()-1}"><button>Last</button></a>
		</t:when>
		<t:otherwise>
			<a href="getalldetails?page=0"><button>First</button></a>
		</t:otherwise>
	</t:choose>	
</p>
	<div style="text-align: center;">
	   <a href="/registration/home"><button>Home</button></a>&nbsp;&nbsp;
	   <a href="/registration/newregistration"><button>Create Account</button> </a>
	</div>
</body>
</html>