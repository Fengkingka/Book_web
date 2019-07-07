<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>图书借记系统——用户管理</title>
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
				<li class=""><a href="index.jsp">首页</a></li>
				<li><a href="refer_book.jsp">查阅图书</a></li>
				<li class="nav3"><a href="borrow_state.jsp">借记情况</a></li>
				<li class="active nav4"><a href="user_manage.jsp">用户管理</a></li>
				<li style="display:none;" class="nav5"><a href="book_manage.jsp">图书管理</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li id="userlogin"><a href="login.jsp">登录</a></li>
				<li id="userexit" style="display:none;"><a href="#" onclick="userexit()">退出</a></li>
			</ul>
			<p class="navbar-text navbar-right weltext">欢迎您</p>
		</div>
	</div>
	  <div class="container">
	    <div class="row">
		      <div class="col-sm-2">
		        <div class="hidden-xs list-group side-bar">
		          <a href="#" class="list-group-item active">密码修改</a>
		        </div>
		      </div>
		      <div class="col-sm-10">
		      		<div class="container pwd_change">
							
								<div class="form-group">
									<label>旧密码</label>
									<input type="password" id="oldpwd" name="oldpassword" class="form-control">
								</div>
								<div class="form-group">
									<label>新密码</label>
									<input type="password" id="newpwd1" name="newpassword1" class="form-control">
								</div>
								<div class="form-group">
									<label>再次输入新密码</label>
									<input type="password" id="newpwd2" name="newpassword2" class="form-control">
								</div>
								<div class="form-group">
									<button class="btn btn-primary btn-block" type="" onclick="pwd_change()">修改</button>
								</div>
							
						</div>
		      </div>
		</div>
		<input type="hidden" id="uid" value="<%=session.getAttribute("user_id")%>">
		<input type="hidden" id="uname" value="<%=session.getAttribute("user_name")%>">
		<input type="hidden" id="utype" value="<%=session.getAttribute("user_type")%>">
		<input type="hidden" id="uflag" value="<%=session.getAttribute("user_flag")%>">
		</div>
		<script type="text/javascript" src="js/index.js"></script>
				<script type="text/javascript">
		//对用户登录结果作出判断
	window.onload = function() { 
		$('#uid').val("");
		$('#uname').val("");
		$('#utype').val("");
		$('#uflag').val("");
		if(<%=session.getAttribute("user_id")%>!=null){
			if("<%=session.getAttribute("user_type")%>"=='1'){
				var haha="";
				haha="<%=session.getAttribute("user_name")%>";
			//	alert(haha);
				$('.weltext').text("欢迎您,"+haha+"");
				$('#uid').val(<%=session.getAttribute("user_id")%>);
				$('#uname').val("<%=session.getAttribute("user_name")%>");
				$('#utype').val(<%=session.getAttribute("user_type")%>);
				$('#uflag').val(<%=session.getAttribute("user_flag")%>);
			//	alert($('#uid').val());
			//	alert($('#utype').val());
				$("#userlogin").hide();
				$("#userexit").show();
				$('.nav3').show();
				$('.nav4').show();
				$('#stu_br_state').show();
				$('#admin_br_state').hide();
				if($('#utype').val()=="1"){
					
				}else if($('#utype').val()=="1"){
					
				}
			}else if("<%=session.getAttribute("user_type")%>"=='0'){
				var haha="";
				haha="<%=session.getAttribute("user_name")%>";
			//	alert(haha);
				$('.weltext').text("欢迎您,"+haha+"");
				$('#uid').val(<%=session.getAttribute("user_id")%>);
				$('#uname').val("<%=session.getAttribute("user_name")%>");
				$('#utype').val(<%=session.getAttribute("user_type")%>);
				$('#uflag').val(<%=session.getAttribute("user_flag")%>);
			//	alert($('#uid').val());
			//	alert($('#utype').val());
				$("#userlogin").hide();
				$("#userexit").show();
				$('.nav3').show();
				$('.nav4').hide();
				$('.nav5').show();
				$('#stu_br_state').hide();
				$('#admin_br_state').show();
			}
			
		}
		}; 
	</script>
</body>
</html>