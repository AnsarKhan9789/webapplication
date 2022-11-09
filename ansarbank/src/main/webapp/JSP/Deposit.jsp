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
<title>Deposit</title>
</head>
<body>

<h1 class="starting">Deposit</h1>
<div class="maincontent">


  <br><br>
  <form action="<%=request.getContextPath( )%>/add" method="post">
  <table>
  <tr><td>
  <label for="AccountNumber">Account</label></td>
  <td> <div class="textboxs"><select name="account">
    <c:forEach items="${listCategory}" var="category">
        <option value="${category}">${category}</option>
    </c:forEach></select></div> 
</td>
</tr>
  <tr><td>
  <label for="Amount">Amount </label>
  </td>
  <td><div class="textboxs"><input name="amount" type="number" min="1" max="100000"required ></div> 
  </td></tr>
  <tr><td><label></label></td> <td><button name="user"  type="Submit" value="MakeDeposit">Deposit</button>
  </td></tr>
  
  </table>

<br><br>
</form>

<label style="color:green">${message }</label>
<label style="color:red">${emessage }</label>
</div>


</body>
</html>