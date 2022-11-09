<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<h1 class="starting">Add User</h1>
	<div class="maincontent">

		<table>
			<form method="post" action="<%=request.getContextPath()%>/add">
				<tr>
					<td><label>Name</label></td>
					<td>
						<div class="textboxs">
							<input name="name" required>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>Email</label></td>
					<td>
						<div class="textboxs">
							<input name="email" required>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>Mobile</label></td>
					<td>
						<div class="textboxs">
							<input name="mobile" required>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>Aadhar</label></td>
					<td>
						<div class="textboxs">
							<input name="Aadhar" required>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>Pan Card</label></td>
					<td>
						<div class="textboxs">
							<input name="PanCard" required>
						</div>
					</td>
				</tr>
				<tr>
					<td><button style="margin-top: 50px" type="submit"
							name="admin" value="Add">Add user</button>
						<button>cancel</button></td>
				</tr>
			</form>
		</table>
		<label>${editMessage}</label>
	</div>


</body>
</html>