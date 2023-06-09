<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>  
<!DOCTYPE html>
<html lang="text/html">
<head>
<meta charset="ISO-8859-1">
<title>Roles List</title>
</head>
<body>
<c:choose>
   <c:when test="${! empty roleList}">
       <table style="margin: auto;background-color: pink;" border="1px">
           <caption>Role list</caption>
           <tr style="background-color: yellow; text-align: center; border: solid;">
               <th>Sl.No</th>
               <th>Role</th>
               <th>Status</th>
               <th>Action</th>
           </tr>
             <c:forEach items="${roleList}" var="t" varStatus="Index">
              <tr>
                <td>${Index.count}</td>
                <td>${t.role}</td>
                <td>${t.roleStatus}</td>
                <ins hidden="${t.roleid}"></ins>
                  <c:choose>
                     <c:when test="${t.roleStatus=='ACTIVE'|| t.roleStatus== 'active'}">
                     <td>
                         <a href="inactive?id=${t.roleid}"><button style="background-color: red;">Inactive</button></a>
                      </td>
                     </c:when>
                     <c:otherwise>
                       <td>
                        <a href="active?id=${t.roleid}"><button style="background-color: green;">Active</button></a>
                       </td> 
                     </c:otherwise>
                  </c:choose>
               </tr>
             </c:forEach>
          
       </table>
   </c:when>
   <c:otherwise>
      <h3 style="text-align: center; color: red;">No data in role Data base</h3>
   </c:otherwise>
</c:choose>
<br>
 <div style="text-align: center;"><a href="/registration/home"><button>Home</button></a></div>
</body>
</html>