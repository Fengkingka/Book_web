<%@page import="dao.StudentDao,dao.AdminDao,beans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*,java.util.*"%>
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
				<li style="display:none;" class="nav6 active"><a href="admin_user_manage.jsp">用户管理</a></li>
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
		       	  <a href="#" class="list-group-item btnuser active" onclick="show_pwduser('btnuser')">学生用户管理</a>
		       	  <a href="#" class="list-group-item btnad" onclick="show_pwduser('btnad')" style="display:none;">管理员用户管理</a>
		          <a href="#" class="list-group-item btnpwd" onclick="show_pwduser('btnpwd')">密码修改</a>
		        </div>
		      </div>
		      <div class="col-sm-10" >
		      		<div class="container pwd_change" id="pwdchange" style="display:none;">
							
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
				<div class="col-sm-10" id="usermanage" >
					<div class="panel panel-info ">
					  <div class="panel-heading">学生账号
							<div class="btn-group" role="group" aria-label="web">
							  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">新增</button>
							  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal2">批量导入</button>
							  <button type="button" class="btn btn-default" onclick="updateuserpwd()">重设密码</button>
							  <button type="button" class="btn btn-default" onclick="updateuserflag('in')">列入黑名单</button>
							  <button type="button" class="btn btn-default" onclick="updateuserflag('out')">移出黑名单</button>
							  <button type="button" class="btn btn-default" onclick="deleteusers()">删除</button>
							</div>
					  </div>
					  <div class="panel-body">
						
						<table class="table table-hover" id="ResultTable">
							<thead>
								<th></th>
							     <th>账号</th>
							     <th>姓名</th>
							     <th>是否为黑名单</th>
							</thead>
						<tbody class="user_info">
								<%
								if((session.getAttribute("user_id"))!=null){
								List <User> users =new AdminDao().queryusers();
								  for(User user:users){
								%>
							 <tr>
							 	<td><input type="checkbox" class="uselect" value="33"></td>
							 	<td><input type="text" class="uid" name="id" value="<%=user.getUserid() %>" size=5 style="border:0px;text-align:center;" readonly="readonly"></td>
								<td><input type="text" class="uname" name="name" value="<%=user.getUserName() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="uflag" name="flag" value="<%=user.getUser_flag() %>" size=5 style="border:0px;text-align:center;"></td>
							 </tr>
								<% 
								}
								}
								%>
						</tbody>
						</table>
						<nav>
			 				 <ul class="pagination" id="table_umanage_ad">
			 				 </ul>
						</nav>
					  </div>
					</div>
		      </div>
		      
		      <div class="col-sm-10" id="adminmanage" style="display:none;">
					<div class="panel panel-info ">
					  <div class="panel-heading">管理员账号
							<div class="btn-group" role="group" aria-label="web">
							  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal3">新增</button>
							  <button type="button" class="btn btn-default" onclick="updateadminpwd()">重设密码</button>
							  <button type="button" class="btn btn-default" onclick="deleteadmins()">删除</button>
							</div>
					  </div>
					  <div class="panel-body">
						
						<table class="table table-hover" id="ResultTable">
							<thead>
								<th></th>
							     <th>账号</th>
							     <th>姓名</th>
							     
							</thead>
						<tbody class="admin_info">
								<%
								if((session.getAttribute("user_id"))!=null){
								List <User> users =new AdminDao().querymanages();
								  for(User user:users){
								%>
							 <tr>
							 	<td><input type="checkbox" class="uselect" value="33"></td>
							 	<td><input type="text" class="uid" name="id" value="<%=user.getUserid() %>" size=5 style="border:0px;text-align:center;" readonly="readonly"></td>
								<td><input type="text" class="uname" name="name" value="<%=user.getUserName() %>" size=5 style="border:0px;text-align:center;"></td>
						<%-- 		<td><input type="text" class="uflag" name="flag" value="<%=user.getUser_flag() %>" size=5 style="border:0px;text-align:center;"></td> --%>
							 </tr>
								<% 
								}
								}
								%>
						</tbody>
						</table>
						<nav>
			 				 <ul class="pagination" id="table_admanage_ad">
			 				 </ul>
						</nav>
					  </div>
					</div>
		      </div>
		      </div>
	 <!-- 批量导入学生账号信息的表单 -->
      <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">批量新增学生用户</h4>
          </div>
          <div class="modal-body">
			  <form action="servlet/ImportStuServlet" method="post" enctype="multipart/form-data" name="usersinsertform">
					<p>上传文件<input type="file" name="uploadfile"></p>
					<!-- <p><input type="submit" name="import" value="批量导入"></p> -->
			 </form>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
            <button class="btn btn-primary" type="button" onclick="insertusers()">批量导入</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
	</div>
	      
	 <!-- 输入学生账号信息的表单 -->
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">新增学生用户</h4>
          </div>
          <div class="modal-body">
          <form action="servlet/InsertuserServlet" method="post" name="userForm">
            <table class="table table-bordered" style="text-align:center;">
			  <tbody>
			     <tr>
			        <td>账号</td> 
			        <td><input type="text" name="user_id" value=""></td> 
			     </tr>
			 	<tr>
			        <td>用户名</td> 
			        <td><input type="text" name="user_name" value=""></td> 
			     </tr>
			  </tbody>
			</table>
			</form>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
            <button class="btn btn-primary" type="button" onclick="insertuser()">提交</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
	</div>
	
	 <!-- 输入管理员账号信息的表单 -->
      <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">新增管理员</h4>
          </div>
          <div class="modal-body">
          <form action="servlet/InsertadminServlet" method="post" name="adminForm">
            <table class="table table-bordered" style="text-align:center;">
			  <tbody>
			     <tr>
			        <td>账号</td> 
			        <td><input type="text" name="user_id" value=""></td> 
			     </tr>
			 	<tr>
			        <td>用户名</td> 
			        <td><input type="text" name="user_name" value=""></td> 
			     </tr>
			  </tbody>
			</table>
			</form>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
            <button class="btn btn-primary" type="button" onclick="insertadmin()">提交</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
	</div>
	
	
		</div>
		<input type="hidden" id="uid" value="<%=session.getAttribute("user_id")%>">
		<input type="hidden" id="uname" value="<%=session.getAttribute("user_name")%>">
		<input type="hidden" id="utype" value="<%=session.getAttribute("user_type")%>">
		<input type="hidden" id="uflag" value="<%=session.getAttribute("user_flag")%>">
		</div>
		<script type="text/javascript" src="js/index.js"></script>
				<script type="text/javascript">
				tableonPage_umanage_ad(1,8);
				tableonPage_admanage_ad(1,8);
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
				$('.nav6').show();
				$(".btnad").hide();
				$('#stu_br_state').hide();
				$('#admin_br_state').show();
			}else if("<%=session.getAttribute("user_type")%>"=='2'){
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
				$('.nav6').show();
				$(".btnad").show();
				$('#stu_br_state').hide();
				$('#admin_br_state').show();
			}
			
		}
		}; 
	</script>
</body>
</html>