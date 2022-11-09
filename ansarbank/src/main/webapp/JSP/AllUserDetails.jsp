<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ tagliburi ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/84625c9b58.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>All user Details</title>
<style>
button {
	border: none;
}

.tables {
	margin-left: 10%;
}
select{
width: 10%;
margin-left:10px;
}
i{
font-size: 20px;
}
</style>
</head>
<body>
	<h1 class="starting">All User Details</h1>

	<div>
		<label>${editMessage1}</label>
		<form action="<%=request.getContextPath()%>/add" method="post">
			<div style="margin-left: 50%; display: inline">
			<button  type="submit" name="admin"
				value="Add">
				<i  class="fa-solid fa-user-plus"></i>
			</button>
		
				<select name="Specification" >

					<option value="All">All</option>
					<option value="Active">Active</option>
					<option value="Inactive">Inactive</option>

				</select>
			</div>

			<button style="" type="submit" name="admin" value="All user details">Show
				Users</button>
		</form>
		</div>
		
		<table class="tables">
			<thead>
				<tr>
					<th>CustomerId</th>
					<th>Name</th>
					<th>Email</th>
					<th>Mobile</th>
					<th>Actions</th>
				</tr>
			</thead>
			<c:set var="map" value="${flag}" />
			<c:choose>
				<c:when test="${map==0}">
					<tbody>
						<tr>
							<td colspan="5">No items to display</td>
						</tr>
					</tbody>

				</c:when>
				<c:otherwise>
					<tbody>
						<c:forEach var="entry" items="${allUserDetailsMap}">
							<form method="post" action="<%=request.getContextPath()%>/add">
							<input name="roleForUser" type="hidden"
										value="${entry.value.getRole()}" readonly>
								<tr>
									<td><input name="userId" value="${entry.key}" readonly></td>
									<td><input name="name" value="${entry.value.getName()}"
										readonly></td>
									<td><input name="email"
										value="${entry.value.getEmailId()}" readonly></td>
									<td><input name="mobile"
										value="${entry.value.getMobileNumber()}" readonly></td>
									<td><button name="admin" type="submit"
											value="Edit the user">
											<i class="fa-solid fa-user-pen"></i>
										</button></td>




								</tr>
							</form>


						</c:forEach>
					</tbody>

				</c:otherwise>
			</c:choose>

		</table>





</body>
</html>