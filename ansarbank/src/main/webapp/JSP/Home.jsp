<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<html>
<head>
<%@ page isELIgnored="false"%>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
	<style>
	.content{
	color:white;
	opacity:0.5;
	width:50%;
	
	
	
	}
	.bgimage{
	
	margin-left:50px;
	height:70vh;
	margin-top:20px;
	padding:50px;
	background-color:black;
	background-image:url("<%=request.getContextPath()%>/images/background.jpeg");
	background-size:100% 100%;
	}
	
	</style>
<title>Home</title>
</head>
<body>
	<h1 style="margin-left:50px;margin-top:30px">Welcome</h1>
	<div class="bgimage">
	<div class="content">
		<h2>
			<span style="border-bottom: 2px solid green; font-size:30px;">Summary</span>
		</h2>
		<p  style="padding-top:30px; font-size:20px;"> 
			<span>Ethereum is a technology for building apps and
				organizations, holding assets, transacting and communicating without
				being controlled by a central authority. There is no need to hand
				over all your personal details to use Ethereum - you keep control of
				your own data and what is being shared. Ethereum has its own
				cryptocurrency, Ether, which is used to pay for certain activities
				on the Ethereum network.</span>
		</p>
	</div>
	</div>
	
</body>
</html>