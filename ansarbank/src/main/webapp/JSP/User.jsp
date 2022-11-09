<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body {
		background: black;
}
label{
color:red;
}
.dropdown{
float:right;
padding-top:20px;
}

.dropdown-content {
  display: none;
  position: absolute;
  min-width: 160px;
  z-index: 1;
  right:0px;
}


.dropdown-content button:hover {background-color: green}

.dropdown:hover .dropdown-content {
  display: block;
  background: black;
}
.dropdown:hover .dropdown-content button {
  border:none;
}

.dropdown:hover .dropbtn {
  background-color: black;
}
.dropdown-content .logout:hover{
  background-color: red;
}

</style>
<script src="https://kit.fontawesome.com/84625c9b58.js" crossorigin="anonymous"></script>
 <link rel = "icon" href = "<%=request.getContextPath( )%>/images/ansarbanklogo.png"
        type = "image/x-icon">
<link rel="stylesheet" href="<%=request.getContextPath( )%>/css/style.css">
  <link rel = "icon" href = "<%=request.getContextPath( )%>/images/ansarbanklogo.png"
        type = "image/x-icon">
<title>Home page</title>
</head>
<body>
<div style="height:100px; background-color: darkcyan;position: -webkit-sticky;
  position: sticky;  top: 0;">



       <div class="dropdown">
  <button style=" border:none;"><i class="fa-regular fa-user" style="font-size:25px"></i></button>
  <div class="dropdown-content">
  <form  class="forms" action="<%=request.getContextPath( )%>/add" method="get"  target="menu" >
       <button  name="targetToMenu"  value="Change Password" type='submit'>Change Password</button><br>
       <button style="padding-left:35px;padding-right:35px; text-align:center"  name="targetToMenu"  value="My details"type='submit'>My Details</button>
      </form>
      <form  class="forms" action="<%=request.getContextPath( )%>/add" method="get"  target="_parent">
       <button class="logout"   style="padding-left:50px;padding-right:50px; text-align:center" name="targetToMenu" value="Logout" type='submit'>Logout</button>
       </form>
  </div>
</div>
<div style="display:flex;margin-left:30px">
 <i style="color:lavender; margin-right:20px; margin-top:20px"class="fa-brands fa-ethereum fa-3x" ></i><h1 style="margin-top:25px;color:lavender">Bank</h1>
</div>
       
</div>
<div style="display:flex" >

<iframe src="JSP/Menu.jsp" style="width:14%;height:100vh;border:none;"></iframe>



<iframe name="menu"src="JSP/Home.jsp" style="width:85%;height:100vh;border:none;"></iframe>
</div>

<%response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
response.setHeader("pragma", "no-cache");
response.setHeader("Expires", "0");

if(session.getAttribute("userId")==null){
	response.sendRedirect("login.jsp");
}
%>

</body>
</html>