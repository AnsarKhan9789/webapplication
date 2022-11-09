<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>

</style>
<link rel="stylesheet" href="<%=request.getContextPath( )%>/css/style.css">

<title>edit</title>
</head>
<body>
<h1 class="starting">Edit</h1>
<div class="maincontent">
<table >
<form method="get" action="<%=request.getContextPath( )%>/add">
<tr>
<td> <label >Name</label></td>
<td>  <input name="name" value="${userDetails.getName()}" ></td>
</tr>
<tr>
<td>  <label >Email</label></td>
<td>  <input name="email" value="${userDetails.getEmailId()}">  </td>
</tr>
<tr>
<td>  <label >Mobile</label></td>
<td>   <input name="mobile" type="tel" pattern="[6-9]{3}[0-9]{7}"value="${userDetails.getMobileNumber() }">  </td>
</tr>
<tr>
<td><button type="submit" name="user" value="save">Save Changes</button>
<button>cancel</button></td>
</tr>
</form>
</table>
</div>
</body>
</html>