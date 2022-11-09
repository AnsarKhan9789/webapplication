<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>


label {
	color: red;
}


</style>
<script src="https://kit.fontawesome.com/84625c9b58.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>Menu</title>
</head>
<body>

			<div class="inner">
				<ul>
					<li><form class="forms" action="../add" method="get"
							target="menu">
							<button name="targetToMenu" value="Home" type='submit'>Home</button>
						</form></li>
					<li><form class="forms" action="../add" method="get"
							target="menu">
							<button name="targetToMenu" value="Deposit" type='submit'>Deposit</button>
						</form></li>
					<li><form class="forms" action="../add" method="get"
							target="menu">
							<button name="targetToMenu" value="Withdraw" type='submit'>Withdraw</button>
						</form></li>
					<li><form class="forms" action="../add" method="get"
							target="menu">
							<button name="targetToMenu" value="Transaction" type='submit'>Transaction</button>
						</form></li>
					<li><form class="forms" action="../add" method="get"
							target="menu">
							<button name="targetToMenu" value="Show Statement" type='submit'>Statement</button>
						</form></li>
					<li><form class="forms" action="../add" method="get"
							target="menu">
							<button name="targetToMenu" value="Account Details" type='submit'>Account
								Details</button>
						</form></li>
					<li><form class="forms" action="../add" method="get"
							target="menu">
							<button name="targetToMenu" value="requestForAccount"
								type='submit'>Request</button>
						</form></li>

				</ul>
			</div>



</body>
</html>