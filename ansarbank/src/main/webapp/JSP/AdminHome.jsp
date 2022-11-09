<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="<%=request.getContextPath( )%>/css/style.css">
<title>Home</title>
</head>
<body>
<div class="inner" >
  <form  class="forms" action="../add" method="get"  target="admin" >
  <ul>
  
  <li>
       <button  name="targetToMenu" value="Home" type='submit'>Home</button>
       </li>
  <li>
       <button  name="admin" value="All User Details"type='submit'>User Details</button>
       </li>
<li>
       <button name="admin" value="Withdrawl Request" type='submit'>Withdrawl Request</button>
       </li>
  <li>
       <button name="admin" value="Non Transaction" type='submit'>Other request</button>
       </li>
       <li><button   name="admin"  value="Account Details" type='submit'>Account Details</button></li>
  <li>
       <button  name="admin" value="Statement" type='submit'>Statement</button>
       </li>

   

  </ul>
  </form>

</div>

</body>
</html>