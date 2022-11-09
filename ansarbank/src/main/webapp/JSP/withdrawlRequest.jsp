<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ tagliburi ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>Request</title>
<style>
.reject{
border:2px solid red;
}
.reject:hover{
background-color:red;
}
</style>
</head>
<body>
<h1 class="starting">WithDrawl Request</h1>
	<table style="margin-left:50px;"class="tables">
		<thead>
			<tr>
				<th>RequestId</th>
				<th>Transaction Id</th>
				<th>Primary Account</th>
				<th>Amount</th>

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


					<c:forEach var="entry" items="${withdrawlRequestMap}">
						<form method="post" action="<%=request.getContextPath()%>/add">
							<tr>
								<td><input name="requestId" value="${entry.key}" readonly></td>
								<td><input name="transactonId"
									value="${entry.value.getTransactionId()}" readonly></td>
								<td><input name="accountNumber"
									value="${entry.value.getAccountNumber()}" readonly></td>
								<td><input name="amount" value="${entry.value.getAmount()}"
									readonly></td>
								<td><button name="admin" type="submit"
										value="Accept the withdrawl">Accept</button>
									<button class="reject" name="admin" type="submit" value="Reject the withdrawl">
										Reject</button></td>

							</tr>

						</form>
					</c:forEach>

				</tbody>
			</c:otherwise>
		</c:choose>

	</table>
</body>
</html>