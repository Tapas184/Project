<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html lang="text/html">
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>View All Plans</title>
</head>
<body>
	<c:choose>
		<c:when test="${!empty page }">
			<table style="border: solid; margin: auto;" border="1">
				<caption>View Plan</caption>
				<tr
					style="color: white; background-color: black; text-align: center;">
					<th>Sl.No</th>
					<th>Plan Name</th>
					<th>Description</th>
					<th>Start Date</th>
					<th>End date</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${page.getContent()}" var="t" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td>${t.planName}</td>
						<td>${t.planDescription}</td>
						<td>${t.startDate}</td>
						<td>${t.endDate}</td>
						<ins hidden="${t.planId}"></ins>
						<td><a href="edit?id=${t.planId}"><button>Edit</button></a> <b>/</b>
							<c:choose>
								<c:when test="${String.valueOf(t.planStatus) =='N'}">
									<a href="change?id=${t.planId}"><button
											style="background-color: rgb(253, 94, 83);">Inactive
										</button></a>
								</c:when>
								<c:otherwise>
									<a href="change?id=${t.planId}"><button
											style="background-color: rgb(118, 255, 122);">Active
										</button></a>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<h1 style="color: red;">Plans are not available</h1>
		</c:otherwise>
	</c:choose>
	<hr>
	<p style="text-align: center;">
		<c:if test="${!page.isFirst()}">
			<a href="view?page=page-1"><button>Prev</button></a>&nbsp;
	</c:if>
		<c:forEach var="i" begin="1" end="${page.getTotalPages()}" step="1">
			<a href="view?page=${i-1}"><button style="size: 1mm;">${i}</button></a>&nbsp;&nbsp;
    </c:forEach>
		<c:choose>
			<c:when test="${!page.isLast()}">
				<a href="view?page=${page.getTotalPages()-1}"><button>Last</button></a>
			</c:when>
			<c:otherwise>
				<a href="view?page=0"><button>First</button></a>
			</c:otherwise>
		</c:choose>
	</p>
	<div style="text-align: center;">
		<a href="/registration/home"><button>Home</button></a>
	</div>
</body>
</html>