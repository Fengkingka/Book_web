<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>图书借记系统——登录</title>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/main1.css">
</head>
<body>
	<div class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<div class="navbar-brand">图书借记系统</div>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="index.jsp">首页</a></li>
				<li><a href="refer_book.jsp">查阅图书</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li ><a href="login.jsp">登录</a></li>
				<li style="display: none;"><a href="#">退出</a></li>
			</ul>
			
		</div>
	</div>
	<div class="container container-small">
	<h1>Login</h1>
		<form action="servlet/LoginServlet" method="post" name="LoginForm">
			<div class="form-group">
				<label>账号</label>
				<input type="text" name="username" class="form-control">
			</div>
			<div class="form-group">
				<label>密码</label>
				<input type="password" name="password" class="form-control">
			</div>
			<div class="form-group">
				<button class="btn btn-primary btn-block" type="button" onclick="login()">登录</button>
			</div>
		</form>
	</div>
	<div class="footer navbar-fixed-bottom">
	© 2019-外包161冯锦华
	</div>
	<%
	String message=null;
	message=(String)session.getAttribute("message");
	if(message=="false"){
	%>
	<script>alert("账号或者密码错误");</script>
	<%
	}session.setAttribute("message", null);
	%>
	<script type="text/javascript" src="js/index.js"></script>	
</body>
</html>