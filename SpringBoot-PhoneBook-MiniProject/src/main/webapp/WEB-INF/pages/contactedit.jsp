<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>  
<!DOCTYPE html>
<html lang="java">
<head>
<meta charset="ISO-8859-1">
<title>Contact Edit</title>
</head>
<body>
<h2 style="text-align: center;color: Blue;text-shadow:1px">Contact Edit Page</h2>
<frm:form modelAttribute="con">
   
   <table style="background-color: pink;margin-left: auto;margin-right: auto;border: solid;" >
      <tr>
       <td colspan="2" style="align-content: center; background-color: yellow;text-align: center;border: solid;">Contact Edit Form</td>
      </tr>
          <tr>
             <td style="text-align: center;">Contact Id :</td>
             <td ><frm:input path="cid" readonly="true"/></td>
          </tr>
          <tr>
             <td style="text-align: center;">Contact Name :</td>
             <td ><frm:input path="name"/></td>
          </tr>
          <tr>
             <td style="text-align: center;">Contact email :</td>
             <td ><frm:input path="email" /></td>
          </tr>
          <tr>
             <td style="text-align: center;">Contact PH.Number :</td>
             <td ><frm:input path="phoneNo" /></td>
          </tr>
          <tr>
             <td style="align-content: center; text-align: center;" colspan="2"><input type="submit" value="Update" onclick="return confirm('Are you sure for Update Contact for : ${con.name}')"> </td>
          </tr>
   </table>
   </frm:form>
   <hr>
  <h5 style="text-align: center;"> <a href="./"><button >Home</button></a>&nbsp;&nbsp;&nbsp;
                                   <a href="addContact"><button >AddContact</button></a>
  </h5> 
  
</body>
</html>