<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style.css">
<title>User</title>
</head>
<body>

<div class="outer" >
<div class="inner" >
  <h1>Menu</h1>
  <ul>
  <li><a href="Home.jsp"target="menu">Home</a></li>
  <li><a href="Deposit.jsp" target="menu" >Deposit </a></li>
  <li><a href="Withdraw.jsp"target="menu" >Withdraw</a></li>
  <li ><a href="Transaction.jsp"target="menu"  >Transaction</a></li>
  <li ><a href="MyDetails.jsp" target="menu" >My details</a></li>
   <li><a href="Statement.jsp"target="menu"  >Show Statement</a></li>
   <li><a href="AccountDetails.jsp"target="menu"  >Account Details</a></li>
   <li><a href="changepassword.jsp"  target="menu">Change password</a></li>
     <li><a href="login.jsp" >Logout</a></li>

  </ul>
</div>
<div class="iframes">
<iframe name="menu"src="Home.jsp" style="width:100%;height:800px;border:none;"></iframe>
</div>
</div>
<footer style="width:100%;height:800px">
<p>Footer</p>
</footer>
</body>
</html>