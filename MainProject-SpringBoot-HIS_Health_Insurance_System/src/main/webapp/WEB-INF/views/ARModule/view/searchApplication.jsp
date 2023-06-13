<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:form method="POST" action="fetchApplication" modelAttribute="model">
  <table>
     <tr>
       <td>Search Id : <c:input path="id" placeholder="Application Id"/>
                       <input type="submit" value="Search">
       </td>
     </tr>
  </table>
</c:form>
</body>
</html>