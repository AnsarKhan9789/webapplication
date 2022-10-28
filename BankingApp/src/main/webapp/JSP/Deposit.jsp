<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style.css">
<title>Deposit</title>
</head>
<body>


<div class="maincontent">
<h1>Deposit page</h1>

  <label for="AccountNumber">Choose a Account</label>
  <select name="AccountNumber" >
    <option value="12345">12345</option>
    <option value="0987">0987</option>
    <option value="8765">8765</option>
  </select>
  <br><br>

<label for="Amount">Amount</label><br>
<input type="number" ><br><br>
 <input type="Submit" value="Deposit">
<a type="button" href="home.html"><button>cancel</button></a>
<br><br>

</div>


</body>
</html>