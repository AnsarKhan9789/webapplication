<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style.css">
<title>Admin</title>
</head>
<body>
<div style="background:black;margin:30px; ">
<h1 style="color:white">Admin</h1>
</div>

<div class="outer">
<div  class="inner">
  <h1>Menu</h1>
  <ul>
  <li><a href="Home.jsp"target="admin">Home</a></li>
   <li><a href="Statement.jsp"target="admin" >Show all Statement</a></li>
 <li><a href="withdrawlRequest.jsp"target="admin" >Show withdrawl Request</a></li>
   <li><a href="NonTransaction.jsp" target="admin">Show Account Request</a></li>
    <li><a href="AllUserDetails.jsp" target="admin">All user details</a></li>
    <li ><a href="MyDetails.jsp" target="admin" >My details</a></li>
     <li><a href="changepassword.jsp"  target="admin">Change password</a></li>
    <li><a href="login.jsp" >Logout</a></li>
  </ul>
</div>
<div class="iframes">
<iframe src="Home.jsp" name="admin" style="width:100%;height:800px; border:none"></iframe>
</div>

</div>
</body>
</html>