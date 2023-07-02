<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <f:form action="/snapPlan/ccapPost" method="POST" modelAttribute="model">
     <table>
     <tr>
        <th colspan="2">CCAP Form</th>
      </tr>
      <tr>
        <td>Plan Name</td>
        <td><f:input path="planName"/>
        </td>
      </tr>
      <tr>
        <td>Married Status</td>
        <td>
          <f:select path="marriedStatus">
             <f:option value="">-Select-</f:option>
             <f:option value="Y">YES</f:option>
             <f:option value="N">NO</f:option>
          </f:select>
        </td>
      </tr> 
      <tr>
        <td>Child Status</td>
        <td>
          <f:select path="childStatus">
             <f:option value="">-Select-</f:option>
             <f:option value="Y">YES</f:option>
             <f:option value="N">NO</f:option>
          </f:select>
        </td>
      </tr> 
      <tr>
        <td>Child Age</td>
        <td><f:input path="childAge" placeholder="If no child put 0"/>
        </td>
      </tr> 
      <tr>
        <td>Child Count</td>
        <td><f:input path="childCount"/></td>
        <ins><f:hidden path="applicationId"/></ins>
        <ins><f:hidden path="caseId"/></ins>
      </tr>
      <tr>
         <td colspan="2"><input type="submit" value="Submit"></td>
      </tr>
     </table>
  </f:form>
</body>
</html>