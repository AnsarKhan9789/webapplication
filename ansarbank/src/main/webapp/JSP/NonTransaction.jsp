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
</head>
<style>
.reject{
border:2px solid red;
}
.reject:hover{
background-color:red;
}
</style>
<body>

<h1 class="starting">Non Transaction</h1>
	<table style="margin-left:50px;"class="tables">
		<thead>
			<tr>
				<th>RequestId</th>
				<th>Primary Account</th>
				<th>Status</th>
				<th>Request Status</th>
				<th>Statement</th>
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
					<c:forEach var="entry" items="${nonTransactionRequestMap}">
						<form method="post" action="<%=request.getContextPath()%>/add">
							<tr>
								<td><input name="requestId" value="${entry.key}" readonly></td>
								<td><input name="accountNumber"
									value="${entry.value.getAccountNumber()}" readonly></td>
								<td><input name="Status" value="${entry.value.getStatus()}"
									readonly></td>
								<td><input name="RequestStatus"
									value="${entry.value.getRequestStatus()}" readonly></td>
								<td><input name="Statement"
									value="${entry.value.getStatement()}" readonly></td>
								<td><button name="admin" type="submit"
										value="acceptNonTrans">Approve</button>
									<button class="reject" name="admin" type="submit" value="rejectNonTrans">Reject</button></td>
						</form>



						</tr>

					</c:forEach>
				</tbody>
			</c:otherwise>
		</c:choose>

	</table>
	<label>${message }</label>

</body>
</html>