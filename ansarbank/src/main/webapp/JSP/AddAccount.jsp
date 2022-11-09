<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath( )%>/css/style.css">
</head>
<body>
<div class="maincontent">
<h1>My details</h1>
<table >
<form method="post" action="<%=request.getContextPath( )%>/add">
<tr>
<td> <label >UserId</label></td>
<td>  <input name="userId" value="${userId }" readonly></td>
</tr>
<tr>
<td>  <label >Branch</label></td>
<td>  <select name="Branch" >
  <option value="Karaikudi">Karaikudi</option>
  <option value="Pudukkottai">Pudukkottai</option>
  <option value="Chennai">Chennai</option>
</select> </td>
</tr>


<tr>
<td>  <label >Opening Balance</label></td>
<td>   <input name="Balance" type="number" min="1000" required>  </td>
</tr>
<tr>
<td><button type="submit" name="admin" value="AddAccount" >Add Account</button>
<button>cancel</button></td>
</tr>
</form>
</table>
<label>${editMessage}</label>
</div>

</body>
</html>