<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:choose>
   <c:when test="${! empty mdl}">
      <table style="border: solid;">
         <tr style="background-color: yellow;" bordercolor="black" >
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
             <td>${col.count}</td>
             <td>${mdl.id}</td>
             <td>${mdl.fname}&nbsp;${mdl.lname}</td>
             <td>${mdl.gender}</td>
             <td>${mdl.mail}</td>
             <td>${mdl.phone}</td>
             <td>${mdl.ssn}</td>
             <ins hidden="${mdl.vrifyStatus}"></ins>
             <c:if test="${mdl.vrifyStatus == 'N' }">
             <td><a href="edit?id=${i.id}"><button>Verify</button></a></td> 
             </c:if>
             <c:if test="${mdl.vrifyStatus == 'Y' }">
             <td><button>Verified</button></td> 
             </c:if>
         </tr>
       </table>  
   </c:when>
   <c:otherwise>
     <h1>No application available</h1>
   </c:otherwise>
</c:choose>
</body>
</html>