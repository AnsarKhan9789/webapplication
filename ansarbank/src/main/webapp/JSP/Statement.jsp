
<%@ tagliburi ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Map"%>
<%@page import="methods.TransactionPojo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>



label {
	margin-right: 40px;
}
.maincontents{
margin-left:10%;
}
</style>


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>statements</title>
</head>
<body>

	<div >
	<h1 class="starting">Statement</h1>
		<form  style="margin-left:50%"action="<%=request.getContextPath()%>/add" method="post">
			
			<label for="AccountNumber">Account</label>
			<div style="margin-top:100px;display:inline;">
				<select name="account" style="width: 20%;">
				<c:forEach items="${listCategory}" var="category">
						<option value="${category}">${category}</option>
					</c:forEach>
				</select>
			</div>


			<button style="margin-left:30px" type="submit" name="user" value="statement">Show
				Statement</button>


		</form>
	</div>

	<div class="maincontents">
		<table class="tables">


			<thead>
				<tr>
					<th>TransactionId</th>
					<th>Id</th>
					<th>From Account</th>
					<th>To Account</th>
					<th>Amount</th>
					<th>Mode</th>
					<th>Status</th>
					<th>Time</th>
					<th>Details</th>
					<th> Balance</th>

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

						<c:forEach var="entry" items="${transactionMap}">

							<tr title="${entry.value.getStatus()}">

								<td>${entry.key}</td>
								<td>${entry.value.getUserId()}</td>
								<td>${entry.value.getPrimaryAccount()}</td>
								<td>${entry.value.getSecondaryAccount()}</td>
								<td>${entry.value.getAmounts()}</td>
								<td>${entry.value.getType()}</td>
								<td>${entry.value.getStatus()}</td>
								<td><jsp:useBean id="date" class="java.util.Date" /> <c:set
										target="${date}" property="time"
										value="${entry.value.getTransactionTime()}" /> <fmt:formatDate
										value="${date}" pattern="dd-MM-yyyy HH:mm" /></td>
								<td>${entry.value.getDetails()}</td>
								<td>${entry.value.getBalance()}/-</td>


							</tr>

						</c:forEach>
					</tbody>
				</c:otherwise>
			</c:choose>
		</table>

	</div>


</body>
</html>