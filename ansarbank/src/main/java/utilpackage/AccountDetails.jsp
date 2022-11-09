<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="<%=request.getContextPath( )%>/css/style.css">
<title>Accounts page</title>
</head>
<body >

<div class="tables" >
<h1>Accounts Details</h1>
<table >
<thead>
<tr>
<th>CustomerId</th>
<th>Account Number</th>
<th>Branch</th>
<th>Balance</th>

</tr>
</thead>
<tbody>
<tr> 
<td>2022101</td>
<td><a href="myaccount.jsp">3022103</a></td>
<td>Karaikudi</td>
<td>22000</td></tr>
<tr> 
<td>2022101</td>
<td><a href="myaccount.jsp">3022104</a></td>
<td>Pudukottai</td>
<td>52000</td></tr>
</tbody>


</table>
</div>


</body>
</html>