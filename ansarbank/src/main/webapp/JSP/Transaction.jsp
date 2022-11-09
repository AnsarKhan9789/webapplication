<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ tagliburi="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="<%=request.getContextPath( )%>/css/style.css">
<style>
td{
padding-bottom:30px;
}
label{
margin-right:40px;
}
</style>
<title>Transaction</title>
</head>
<body>
<h1 class="starting">Transaction</h1>
<div class="maincontent">
<br><br>

  <form action="<%=request.getContextPath( )%>/add" method="post">
  <table>
  <tr><td>
  <label for="AccountNumber">Sender Account</label>

  
  </td>
  <td> <div class="textboxs">
  <select name="account">
    <c:forEach items="${listCategory}" var="category">
        <option value="${category}">${category}</option>
    </c:forEach>
</select>
  </div> </td>
  </tr>
  <tr>
  <td><label for="recieverAccountNumber"> Reciever Account</label></td>
  <td>
  <div class="textboxs">  <input name="recieverAccountNumber" type="number"  min="1" max="100000"required>
  </div>

 </td>
  </tr>
  <tr>   <td><label for="Amount">Amount</label></td>
  <td><div class="textboxs"><input name="amount" type="number" pattern="[0-9]{6}" required></div></td></tr>
  
  <tr><td><label></label></td>
  <td> <button name="user" type="Submit" value="Transaction">Transaction</button></td></tr>
  </table>

  </form>


<label style="color:green">${message }</label>
<label style="color:red">${emessage }</label>
 <br><br>
</div>


</body>
</html>