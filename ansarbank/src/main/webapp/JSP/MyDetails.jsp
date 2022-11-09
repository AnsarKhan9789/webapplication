<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ tagliburi="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/84625c9b58.js" crossorigin="anonymous"></script>
<title>My details</title>
<style>
.maincontent{
margin-top:10%;
margin-left:20%;

}
.shown{
background:none;
width:100%;
height:40px;
font-size:20px;
color:white;
border:none;
outline: none;

}
.textboxs{
display:inline;
border-bottom:2px solid green;
}
.nonshown{
background-color:dimgray;
height:40px;
font-size:20px;
}
label{
color:white;
font-size:20px;
}
button {
	background: none;
	border: 2px solid green;
	color: white;
	text-align: center;
	padding: 10px;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	margin: 4px 2px;
	cursor: pointer;
	text-decoration: none;
}

button:hover {
	background: green;
}


</style>
</head>
<body>
<h1 class="starting">Details</h1>
<div class="maincontent">


<table >
<form method="post" action="<%=request.getContextPath( )%>/add">
<input type="hidden" class="shown" name="oldname" value="${userDetails.getName()}" >
<input type="hidden" class="shown" name="oldemail" value="${userDetails.getEmailId()}" >
<input type="hidden" class="shown" name="oldnumber" value="${userDetails.getMobileNumber()}" >
<tr>
<td> <label >UserId</label></td>
<td>  <div class="textboxs"><input class="shown" name="UserId" value="${userDetails.getUserId()}" readonly></div></td>
</tr>
<tr>
<td> <label >Name</label></td>
<td>  <div class="textboxs"> <input class="shown" name="name" value="${userDetails.getName()}" ></div></td>
<td><i class="fa-solid fa-pen" style="color:white"></i></td>
</tr>
<tr>
<td>  <label >Email</label></td>
<td>  <div class="textboxs"><input class="shown" name="email" value="${userDetails.getEmailId()}"></div>  </td>
<td><i class="fa-solid fa-pen" style="color:white"></i></td>
</tr>
<tr>
<td>  <label >Mobile</label></td>
<td>  <div class="textboxs"> <input class="shown" name="mobile" value="${userDetails.getMobileNumber()}" pattern="[6-9]{3}[0-9]{7}"> </div> </td>
<td><i class="fa-solid fa-pen" style="color:white"></i></td>
</tr>
<td>  <label >Role</label></td>
<td> <div class="textboxs"><input  class="shown" name="role" value="${userDetails.getRole()}" readonly></div>  </td>
</tr>

<c:choose>
    <c:when test="${roles.equals('user')}">
<tr>
<td>  <label >Aadhar</label></td>
<td>  <div class="textboxs"> <input class="shown" name="aadhar" value="${userDetails.getAadharCard()}"readonly></div></td>
</tr>
<tr>
<td>  <label >Pan</label></td>
<td>  <div class="textboxs"> <input class="shown" name="Pan" value="${userDetails.getPanCard()}"readonly></div></td>
</tr>
<tr>
<td>  <label >Status</label></td>
<td>  <div class="textboxs"> <input class="shown" name="status" value="${userDetails.getUserStatus() }"readonly></div></td>
</tr>
    </c:when>
</c:choose>
<tr>
<td><button type="submit" name="user" value="edit" >Save</button>
<button type="submit" name="user" value="Cancel for edit">cancel</button></td>
</tr>
</form>
</table>
<label style="color:red">${editMessage}</label>
</div>


</body>
</html>