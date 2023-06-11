<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="text/html">
<head>
<meta charset="ISO-8859-1">
<title>Roles List</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty page}">
			<table style="margin: auto; background-color: pink;" border="1">
				<caption>Role list</caption>
				<tr
					style="background-color: yellow; text-align: center; border: solid;">
					<th>Sl.No</th>
					<th>Role</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${page.getContent()}" var="t" varStatus="Index">
					<tr>
						<td>${Index.count}</td>
						<td>${t.role}</td>
						<td>${t.roleStatus}</td>
						<ins hidden="${t.roleid}"></ins>
						<c:choose>
							<c:when
								test="${t.roleStatus=='ACTIVE'|| t.roleStatus== 'active'}">
								<td><a href="inactive?id=${t.roleid}"><button
											style="background-color: rgb(63, 255, 0);">Active</button></a></td>
							</c:when>
							<c:otherwise>
								<td><a href="active?id=${t.roleid}"><button
											style="background-color: rgb(255, 110, 74);">In-Active</button></a>
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>

			</table>
		</c:when>
		<c:otherwise>
			<h3 style="text-align: center; color: red;">No data in role Data
				base</h3>
		</c:otherwise>
	</c:choose>
	<hr>
	<p style="text-align: center;">
		<c:if test="${!page.isFirst()}">
			<a href="showRoles?page=page-1"><button>Prev</button></a>&nbsp;
	</c:if>
		<c:forEach var="i" begin="1" end="${page.getTotalPages()}" step="1">
			<a href="showRoles?page=${i-1}"><button style="size: 1mm;">${i}</button></a>&nbsp;&nbsp;
    </c:forEach>
		<c:choose>
			<c:when test="${!page.isLast()}">
				<a href="showRoles?page=${page.getTotalPages()-1}"><button>Last</button></a>
			</c:when>
			<c:otherwise>
				<a href="showRoles?page=0"><button>First</button></a>
			</c:otherwise>
		</c:choose>
	</p>
	<br>
	<div style="text-align: center;">
		<a href="/registration/home"><button>Home</button></a>
	</div>
</body>
</html>