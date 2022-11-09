
<%@ tagliburi ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account Details</title>


<link rel="stylesheet" href="<%=request.getContextPath( )%>/css/style.css">

  <style>
  .maincontents{
  margin-left:20%;
  margin-top:8%;
  }
  </style>


</head>
<body>
<p>${message }</p>
<h1 class="starting">Account Details</h1>
<div class="maincontents">


	<table class="tables">
		<thead>
			<tr>
				<th>Customer Id</th>
				<th>Account Number</th>
				<th>Balance</th>
				<th>Branch</th>
				<th>Account Status</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="entry" items="${accountMap}">
				<form method="post" action="<%=request.getContextPath( )%>/add">
				<tr>

					<td><input type="hidden" name="user" value="showUserDetails"><input type="submit" name="userId"
						value="${entry.value.getUserId()}" readonly></td>
					<td>${entry.value.getAccountNumber()}</td>
					<td>${entry.value.getBalance()}</td>
					<td>${entry.value.getBranch()}</td>
					<td>${entry.value.getAccountStatus()}</td>


				</tr>
				</form>




			</c:forEach>
		</tbody>

	</table>
	</div>
</body>
</html>