<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="text/html">
<head>
<meta charset="ISO-8859-1">
<title>View All Plans</title>
</head>
<body>
	<c:choose>
		<c:when test="${!empty modellist }">
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
					<c:forEach items="${modellist}" var="t" varStatus="index">
						<tr>
						    <td>${index.count}</td>
							<td>${t.planName}</td>
							<td>${t.planDescription}</td>
							<td>${t.startDate}</td>
							<td>${t.endDate}</td>
							<ins hidden="${t.planId}"></ins>
							<td>
							<a href="edit?id=${t.planId}"><button>Edit</button></a>
							<b>/</b> 
								<c:choose>
									<c:when test="${String.valueOf(t.planStatus) =='N'}">
										<a href="change?id=${t.planId}" onclick="return confirm('Are your sure want to Active plan?')"><button
												style="background-color: rgb(253, 94, 83);">Inactive</button></a>
									</c:when>
									<c:otherwise>
										<a href="change?id=${t.planId}" onclick="return confirm('Are your sure want to In-Active plan?')"><button
												style="background-color: rgb(118, 255, 122);">Active</button></a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<h1 style="color: red;">Plans are not available</h1>
		</c:otherwise>
	</c:choose>
	<hr>
	<div style="text-align: center;">
	<a href="/registration/home"><button>Home</button></a>
	</div>
</body>
</html>