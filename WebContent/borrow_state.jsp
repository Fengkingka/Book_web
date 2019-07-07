<%@page import="dao.StudentDao,dao.AdminDao,beans.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.sql.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>图书借记系统——借记状态</title>
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
				<li class="active nav3"><a href="borrow_state.jsp">借记情况</a></li>
				<li class="nav4"><a href="user_manage.jsp">用户管理</a></li>
				<li style="display:none;" class="nav5"><a href="book_manage.jsp">图书管理</a></li>
				<li style="display:none;" class="nav6"><a href="admin_user_manage.jsp">用户管理</a></li>
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
		          <a href="#" class="list-group-item btn_bobook active" onclick="showinfo('btn_bobook')">在借图书</a>
		          <a href="#" class="list-group-item btn_rebook" onclick="showinfo('btn_rebook')">已归还图书</a>
		          <a href="#" class="list-group-item btn_mybobook" onclick="showinfo('btn_mybobook')" style="display:none;">我的在借图书</a>
		          <a href="#" class="list-group-item btn_myrebook" onclick="showinfo('btn_myrebook')" style="display:none;">我的归还图书</a>
		        </div>
		      </div>
		      	<!-- 学生登录的显示 -->
		      <div class="col-sm-10" id="stu_br_state" style="display:none;">
					<div class="panel panel-info bobookinfo">
					  <div class="panel-heading">在借图书
					  		<div class="btn-group" role="group" aria-label="web">
							  <button type="button" class="btn btn-default" onclick="stu_rebook()">归还</button>
							  <button type="button" class="btn btn-default" onclick="stu_xubook()">续借</button>
							<!--   <button type="button" class="btn btn-default" onclick="deletebook()">删除</button> -->
							</div>
					  </div>
					  <div class="panel-body">
						
						<table class="table table-hover" id="ResultTable">
							<thead>
								<th></th>
							     <th>图书编号</th>
							     <th>书名</th>
							     <th>ISBN</th>
							     <th>作者</th>
							     <th>类型</th>
							     <th>出版社</th>
							     <th>定价</th>
							     <th>应归还日期</th>
							     <!-- <th>在库数量</th>
							     <th>入库时间</th>
							 	 <th>可借状态</th> -->
							</thead>
						<tbody class="tablestu_bobook">
								<%
								if((session.getAttribute("user_id"))!=null){
								List <Book> books =new StudentDao().queryBoBooks(session.getAttribute("user_id").toString());
								  for(Book book:books){
								%>
							 <tr >
							 	<td><input type="checkbox" name="" value="33"></td>
							 	<td><input type="text" class="bid botext" name="id" value="<%=book.getBook_id() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bname botext" name="name" value="<%=book.getBook_name() %>" size=8 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bISBN botext" name="ISBN" value="<%=book.getBook_ISBN() %>" size=8 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bauthor botext" name="author" value="<%=book.getBook_author() %>" size=6 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="btype botext" name="type" value="<%=book.getBook_type()%>" size=6 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bpress botext" name="press" value="<%=book.getBook_press() %>" size=7 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bprice botext" name="price" value="<%=book.getBook_price() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bsredate botext" name="sredate" value="<%=book.getBook_s_return_date() %>" size=6 style="border:0px;text-align:center;"></td>
					<%-- 			<td><input type="text" class="bnum" name="num" value="<%=book.getBook_num() %>" size=5></td>
								<td><input type="text" class="btime" name="time" value="<%=book.getBook_time() %>" size=5></td>
								<td><input type="text" class="bstate" name="state" value="<%=book.getBook_state() %>" size=2></td> --%>
							 </tr>
								<% 
								}
								}
								%>
						</tbody>
						</table>
						<nav>
			 				 <ul class="pagination" id="table_bo_stu">
			 				 </ul>
						</nav>
						<form action="servlet/UpdateServlet" method="post" name="ResultForm" >		
							<input type="hidden" name="set" value="" class="UpdateForm">
						</form>
						<form action="servlet/DeleteServlet" method="post" name="ResultForm1">
							<input type="hidden" name="set" value="" class="DeleteForm">
						</form>	
					  </div>
					</div>
					<div class="panel panel-info rebookinfo" style="display:none;">
					  <div class="panel-heading">已归还图书
					  </div>
					  <div class="panel-body">
						
						<table class="table table-hover" id="ResultTable" >
							<thead>
								<th></th>
							     <th>图书编号</th>
							     <th>书名</th>
							     <th>ISBN</th>
							     <th>作者</th>
							     <th>类型</th>
							     <th>出版社</th>
							     <th>定价</th>
							     <th>归还日期</th>
							    <!--  <th>在库数量</th>
							     <th>入库时间</th>
							 	 <th>可借状态</th> -->
							</thead>
						<tbody class="tablestu_rebook">
								<%
								if((session.getAttribute("user_id"))!=null){
								List <Book> books1 =new StudentDao().queryReBooks(session.getAttribute("user_id").toString());
								  for(Book book:books1){
								%>
							 <tr >
							 	<td><input type="checkbox" name="" value="33"></td>
							 	<td><input type="text" class="bid retext" name="id" value="<%=book.getBook_id() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bname retext" name="name" value="<%=book.getBook_name() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bISBN retext" name="ISBN" value="<%=book.getBook_ISBN() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bauthor retext" name="author" value="<%=book.getBook_author() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="btype retext" name="type" value="<%=book.getBook_type()%>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bpress retext" name="press" value="<%=book.getBook_press() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bprice retexts" name="price" value="<%=book.getBook_price() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="btredate retexts" name="tredate" value="<%=book.getBook_t_return_date() %>" size=7 style="border:0px;text-align:center;"></td>
								<%-- <td><input type="text" class="bnum" name="num" value="<%=book.getBook_num() %>" size=5></td>
								<td><input type="text" class="btime" name="time" value="<%=book.getBook_time() %>" size=5></td>
								<td><input type="text" class="bstate" name="state" value="<%=book.getBook_state() %>" size=2></td> --%>
							 </tr>
								<% 
								}
								}
								%>
						</tbody>
						</table>
						<nav>
			 				 <ul class="pagination" id="table_re_stu">
			 				 </ul>
						</nav>
						<form action="servlet/UpdateServlet" method="post" name="ResultForm" >		
							<input type="hidden" name="set" value="" class="UpdateForm">
						</form>
						<form action="servlet/DeleteServlet" method="post" name="ResultForm1">
							<input type="hidden" name="set" value="" class="DeleteForm">
						</form>	
					  </div>
					</div>
		      </div>
		      <!-- 管理员的借记情况查看 -->
		      <div class="col-sm-10" id="admin_br_state" style="display:none;">
					<div class="panel panel-info bobookinfo">
					  <div class="panel-heading">在借图书
					  </div>
					  <div class="panel-body">
						
						<table class="table table-hover" id="ResultTable">
							<thead>
								<th></th>
							     <th>图书编号</th>
							     <th>书名</th>
							     <th>ISBN</th>
							     <th>作者</th>
							     <th>类型</th>
							     <th>出版社</th>
							     <th>定价</th>
							     <th>在库数量</th>
							     <th>借出数量</th>
							     <th>被借次数</th>
							     <th>借阅人id</th>
							</thead>
						<tbody class="tablebook">
								<%
								if((session.getAttribute("user_id"))!=null){
								List <Book> books =new AdminDao().queryBoBooks("1");
								  for(Book book:books){
								%>
							 <tr >
							 	<td><input type="checkbox" name="" value="33"></td>
							 	<td><input type="text" class="bid botext" name="id" value="<%=book.getBook_id() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bname botext" name="name" value="<%=book.getBook_name() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bISBN botext" name="ISBN" value="<%=book.getBook_ISBN() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bauthor botext" name="author" value="<%=book.getBook_author() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="btype botext" name="type" value="<%=book.getBook_type()%>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bpress botext" name="press" value="<%=book.getBook_press() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bprice botext" name="price" value="<%=book.getBook_price() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bpress botext" name="num" value="<%=book.getBook_num() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bprice botext" name="bonum" value="<%=book.getBook_bonum() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bhisnum botext" name="bhnum" value="<%=book.getBook_hisnum() %>" size=5 style="border:0px;text-align:center;"></td>
						 		<td><input type="text" class="buserid botext" name="buid" value="<%=book.getBook_userid() %>" size=5 style="border:0px;text-align:center;"></td>
			
							 </tr>
								<% 
								}
								}
								%>
						</tbody>
						</table>
						 <nav>
			 				 <ul class="pagination" id="table_bo_ad">
			 				 </ul>
						</nav> 
						<form action="servlet/UpdateServlet" method="post" name="ResultForm" >		
							<input type="hidden" name="set" value="" class="UpdateForm">
						</form>
						<form action="servlet/DeleteServlet" method="post" name="ResultForm1">
							<input type="hidden" name="set" value="" class="DeleteForm">
						</form>	
					  </div>
					</div>
					<div class="panel panel-info rebookinfo" style="display:none;">
					  <div class="panel-heading">已归还图书
					  </div>
					  <div class="panel-body">
						
						<table class="table table-hover" id="ResultTable" >
							<thead>
								<th></th>
							     <th>图书编号</th>
							     <th>书名</th>
							     <th>ISBN</th>
							     <th>作者</th>
							     <th>类型</th>
							     <th>出版社</th>
							     <th>定价</th>
							    <th>在库数量</th>
							     
							 	 <th>归还人id</th>
							</thead>
						<tbody class="tablebook2">
								<%
								if((session.getAttribute("user_id"))!=null){
								List <Book> books1 =new AdminDao().queryReBooks("0");
								  for(Book book:books1){
								%>
							 <tr >
							 	<td><input type="checkbox" name="" value="33"></td>
							 	<td><input type="text" class="bid retext" name="id" value="<%=book.getBook_id() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bname retext" name="name" value="<%=book.getBook_name() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bISBN retext" name="ISBN" value="<%=book.getBook_ISBN() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bauthor retext" name="author" value="<%=book.getBook_author() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="btype retext" name="type" value="<%=book.getBook_type()%>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bpress retext" name="press" value="<%=book.getBook_press() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bprice retexts" name="price" value="<%=book.getBook_price() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bnum retext" name="num" value="<%=book.getBook_num() %>" size=5 style="border:0px;text-align:center;"></td>
						<%-- 		<td><input type="text" class="brenum retexts" name="renum" value="<%=book.getBook_renum() %>" size=5 style="border:0px;text-align:center;"></td> --%>
							 	<td><input type="text" class="buserid retexts" name="buid" value="<%=book.getBook_userid() %>" size=5 style="border:0px;text-align:center;"></td>
							 </tr>
								<% 
								}
								}
								%>
						</tbody>
						</table>
						<nav>
			 				 <ul class="pagination" id="table_re_ad">
			 				 </ul>
						</nav>
						<form action="servlet/UpdateServlet" method="post" name="ResultForm" >		
							<input type="hidden" name="set" value="" class="UpdateForm">
						</form>
						<form action="servlet/DeleteServlet" method="post" name="ResultForm1">
							<input type="hidden" name="set" value="" class="DeleteForm">
						</form>	
					  </div>
					</div>
					
					<!-- 超级管理员的在借图书 -->
					<div class="panel panel-info mybobookinfo" style="display:none;">
					  <div class="panel-heading">我的在借图书
					  		<div class="btn-group" role="group" aria-label="web">
							  <button type="button" class="btn btn-default" onclick="admin_rebook()">归还</button>
							  <button type="button" class="btn btn-default" onclick="admin_xubook()">续借</button>
							<!--   <button type="button" class="btn btn-default" onclick="deletebook()">删除</button> -->
							</div>
					  </div>
					  <div class="panel-body">
						
						<table class="table table-hover" >
							<thead>
								<th></th>
							     <th>图书编号</th>
							     <th>书名</th>
							     <th>ISBN</th>
							     <th>作者</th>
							     <th>类型</th>
							     <th>出版社</th>
							     <th>定价</th>
							     <th>应归还日期</th>
							     <!-- <th>在库数量</th>
							     <th>入库时间</th>
							 	 <th>可借状态</th> -->
							</thead>
						<tbody class="tablead_bobook">
								<%
								if((session.getAttribute("user_id"))!=null){
								List <Book> books =new AdminDao().adminqueryBoBooks(session.getAttribute("user_id").toString());
								  for(Book book:books){
								%>
							 <tr >
							 	<td><input type="checkbox" name="" value="33"></td>
							 	<td><input type="text" class="bid botext" name="id" value="<%=book.getBook_id() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bname botext" name="name" value="<%=book.getBook_name() %>" size=6 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bISBN botext" name="ISBN" value="<%=book.getBook_ISBN() %>" size=8 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bauthor botext" name="author" value="<%=book.getBook_author() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="btype botext" name="type" value="<%=book.getBook_type()%>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bpress botext" name="press" value="<%=book.getBook_press() %>" size=7 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bprice botext" name="price" value="<%=book.getBook_price() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bsredate botext" name="sredate" value="<%=book.getBook_s_return_date() %>" size=6 style="border:0px;text-align:center;"></td>
					<%-- 			<td><input type="text" class="bnum" name="num" value="<%=book.getBook_num() %>" size=5></td>
								<td><input type="text" class="btime" name="time" value="<%=book.getBook_time() %>" size=5></td>
								<td><input type="text" class="bstate" name="state" value="<%=book.getBook_state() %>" size=2></td> --%>
							 </tr>
								<% 
								}
								}
								%>
						</tbody>
						</table>
						<nav>
			 				 <ul class="pagination" id="table_mybo">
			 				 </ul>
						</nav>
						
					  </div>
					</div>
					<!-- 超级管理员的归还图书 -->
					<div class="panel panel-info myrebookinfo" style="display:none;">
					  <div class="panel-heading">我的归还图书
					  		<div class="btn-group" role="group" aria-label="web">
							  
							<!--   <button type="button" class="btn btn-default" onclick="deletebook()">删除</button> -->
							</div>
					  </div>
					  <div class="panel-body">
						
						<table class="table table-hover" >
							<thead>
								<th></th>
							     <th>图书编号</th>
							     <th>书名</th>
							     <th>ISBN</th>
							     <th>作者</th>
							     <th>类型</th>
							     <th>出版社</th>
							     <th>定价</th>
							     <th>归还日期</th>
							     <!-- <th>在库数量</th>
							     <th>入库时间</th>
							 	 <th>可借状态</th> -->
							</thead>
						<tbody class="tablead_rebook">
								<%
								if((session.getAttribute("user_id"))!=null){
								List <Book> books =new AdminDao().adminqueryReBooks(session.getAttribute("user_id").toString());
								  for(Book book:books){
								%>
							 <tr >
							 	<td><input type="checkbox" name="" value="33"></td>
							 	<td><input type="text" class="bid botext" name="id" value="<%=book.getBook_id() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bname botext" name="name" value="<%=book.getBook_name() %>" size=6 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bISBN botext" name="ISBN" value="<%=book.getBook_ISBN() %>" size=8 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bauthor botext" name="author" value="<%=book.getBook_author() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="btype botext" name="type" value="<%=book.getBook_type()%>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bpress botext" name="press" value="<%=book.getBook_press() %>" size=7 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bprice botext" name="price" value="<%=book.getBook_price() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="btredate retexts" name="tredate" value="<%=book.getBook_t_return_date() %>" size=7 style="border:0px;text-align:center;"></td>
					<%-- 			<td><input type="text" class="bnum" name="num" value="<%=book.getBook_num() %>" size=5></td>
								<td><input type="text" class="btime" name="time" value="<%=book.getBook_time() %>" size=5></td>
								<td><input type="text" class="bstate" name="state" value="<%=book.getBook_state() %>" size=2></td> --%>
							 </tr>
								<% 
								}
								}
								%>
						</tbody>
						</table>
						<nav>
			 				 <ul class="pagination" id="table_myre">
			 				 </ul>
						</nav>
						
					  </div>
					</div>
					
		      </div>
	     </div>
      </div>
      <!-- 输入图书信息的表单 -->
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">新增图书</h4>
          </div>
          <div class="modal-body">
          <form action="servlet/InputServlet" method="post" name="ResultForm2">
            <table class="table table-bordered" style="text-align:center;">
			  <tbody>
			     <tr>
			        <td>图书编号</td> 
			        <td><input type="text" name="book_id" value=""></td> 
			     </tr>
			 	<tr>
			        <td>图书标准码</td> 
			        <td><input type="text" name="book_ISBN" value=""></td> 
			     </tr>
			 	<tr>
			        <td>书名</td> 
			        <td><input type="text" name="book_name" value=""></td> 
			    </tr>
			 	<tr>
			        <td>作者</td> 
			        <td><input type="text" name="book_author" value=""></td> 
			    </tr>
			 	<tr>
			        <td>图书类型</td> 
			        <td><input type="text" name="book_type" value=""></td> 
			    </tr>
			    <tr>
			        <td>出版社</td> 
			        <td><input type="text" name="book_press" value=""></td> 
			    </tr>
			    <tr>
			        <td>定价</td> 
			        <td><input type="text" name="book_price" value=""></td> 
			    </tr>
			    <tr>
			        <td>在库数量</td> 
			        <td><input type="text" name="book_num" value=""></td> 
			    </tr>
			    <tr>
			        <td>入库时间</td> 
			        <td><input type="text" name="book_time" value=""></td> 
			    </tr>
			    <tr>
			        <td>内容简介</td> 
			        <td><input type="text" name="book_content" value=""></td> 
			    </tr>
			  </tbody>
			</table>
			</form>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
            <button class="btn btn-primary" type="button" onclick="insertbook()">提交</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
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
				tableonPage_bo_stu(1,8);
				tableonPage_re_stu(1,8);
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
				tableonPage_bo_ad(1,8);
				tableonPage_re_ad(1,8);
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
				$('#stu_br_state').hide();
				$('#admin_br_state').show();
			}else if("<%=session.getAttribute("user_type")%>"=='2'){
				tableonPage_bo_ad(1,8);
				tableonPage_re_ad(1,8);
				tableonPage_mybo_ad(1,8);
				tableonPage_myre_ad(1,8);
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
				$('#stu_br_state').hide();
				$('#admin_br_state').show();
				$(".btn_mybobook").show();
				$(".btn_myrebook").show();
			}
		}
		}; 
	</script>
</body>
</html>