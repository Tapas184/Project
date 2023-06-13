<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<span>${result}</span>
<c:choose>
   <c:when test="${! empty listOfApplications}">
      <table style="border: solid;">
         <tr style="background-color: yellow;" >
           <th>Sl.No</th>
           <th>Application No</th>
           <th>UserName</th>
           <th>Gender</th>
           <th>Mail Id</th>
           <th>Phone.No</th>
           <th>SSN</th>
           <th>Action</th>
         </tr>
         <tr style="text-align: left;">
         <c:forEach items="${listOfApplications}" var="i" varStatus="Index">
             <td>${Index.count}</td>
             <td>${i.id}</td>
             <td>${i.fname}&nbsp;${i.lname}</td>
             <td>${i.gender}</td>
             <td>${i.mail}</td>
             <td>${i.phone}</td>
             <td>${i.ssn}</td>
             <td><a href="delete?id=${i.id}"><button>Delete</button></a></td> 
         </c:forEach>
         </tr>
      </table>
   </c:when>
   <c:otherwise>
      <h2 style="color: red;">Empty List</h2>
   </c:otherwise>
</c:choose>
</body>
</html>