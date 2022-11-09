<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
table td:nth-child(even){
padding-left:30px;

}
table td:last-of-type{
padding-bottom:30px;
}
table td button{
padding-left:50px;
padding-right:50px;
}

</style>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">

<title>Change Password</title>
</head>
<body>
<h1 style="margin-left:30px;margin-top:30px">Change password</h1>
	<div class="maincontent">
		
		<table>
			<form action="<%=request.getContextPath()%>/add" method="post" >
			<tr>
				<td><label>Old password</label></td>
				<td><div class="textboxs"><input type="password" name="oldPassword" required></div></td>
			</tr>
			<tr>
				<td><span style="color: red">${messageForOld} </span></td>
			</tr>

			<tr>
				<td><label>New password</label></td>
				<td><div class="textboxs"><input type="password" name="newPassword" required></div></td>
			</tr>
			<tr>
				<td><span id="message" style="color: red"> </span></td>
			</tr>
			<tr>
				<td><label>Confirm password</label></td>
				<td><div class="textboxs"><input type="password" name="confirmPassword" required></div></td>
			</tr>
			<tr>
				<td><span id="message" style="color: red"> </span></td>
			</tr>
			<tr class="last"><td></td>
				<td><button><input type="submit" name="user" value="Change password"></button></td>

			</tr>
			</form>
		
		</table>
		<label>	${message }</label>

	</div>



</body>
</html>