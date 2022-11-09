<%@ tagliburi ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<style>
.maincontents {
	margin-left: 20%;
	margin-top: 10%;
}
select{
width: 20%;
}
button{
margin-left:20px;
padding:5px;
}
.shown{
margin-left:20%;
margin-top: 10%;
}
</style>
<title>Deposit</title>
</head>
<body>
	<h1 class="starting">Request</h1>


	<div class="maincontents">

		<form action="<%=request.getContextPath()%>/add" method="post">
			<div >
				<select  name="account" required>
					<option value="" selected disabled hidden> Account</option>
					<c:forEach items="${accountList}" var="list">
						<option value="${list}">${list}</option>
					</c:forEach>
				</select>
				<button name="user" type="Submit" value="MakeRequest">Request
				to activate</button>
			</div>
		</form>

		<label style="color: green">${message }</label> <label
			style="color: red">${emessage }</label>
	</div>
	<c:set var="map" value="${flag}" />
	<c:choose>
		<c:when test="${map==0}">
<h1 class="shown">No Request Is Sent</h1>
		</c:when>
		<c:otherwise>

			<table style="margin-left: 20%" class="tables">
				<thead>
					<tr>
						<th>RequestId</th>
						<th>Primary Account</th>
						<th>Status</th>
						<th>Request Status</th>
						<th>Statement</th>


					</tr>
				</thead>

				<tbody>
					<c:forEach var="entry" items="${transactionMap}">
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
						</form>



						</tr>

					</c:forEach>
				</tbody>
				</c:otherwise>
				</c:choose>





			</table>
</body>
</html>