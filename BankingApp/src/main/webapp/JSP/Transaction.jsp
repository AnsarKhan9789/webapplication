<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style.css">
<title>Transaction</title>
</head>
<body>
<h1>Transaction page</h1>
<div class="maincontent">
  <label for="AccountNumber">Choose a Account</label>
  <select name="AccountNumber" >
    <option value="12345">12345</option>
    <option value="0987">0987</option>
    <option value="8765">8765</option>
  </select>
  <br><br>
<label for="AccountNumber">Account Number</label><br>
<input type="number" ><br>
<label for="UserId">Amount</label><br>
<input type="number" ><br>
 <input type="Submit" value="Transfer">
  
<button>cancel</button>
 <br><br>
</div>


</body>
</html>