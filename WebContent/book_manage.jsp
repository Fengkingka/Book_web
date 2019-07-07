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
				<li class="nav3"><a href="borrow_state.jsp">借记情况</a></li>
				<li class="nav5 active"><a href="book_manage.jsp">图书管理</a></li>
				<li class="nav4" style="display:none;"><a href="user_manage.jsp">用户管理</a></li>
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
		          <a href="#" class="list-group-item active" onclick="">图书管理</a>
		        </div>
		      </div>
		      <!-- 管理员的图书管理 -->
		      <div class="col-sm-10" id="admin_br_state" style="display:none;">
					<div class="panel panel-info bobookinfo">
					  <div class="panel-heading">在库图书
							<div class="btn-group" role="group" aria-label="web">
							  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">新增</button>
							  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal2">批量新增</button>
							  <button type="button" class="btn btn-default" onclick="updatebook()">修改</button>
							  <button type="button" class="btn btn-default" onclick="deletebook()">删除</button>
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
							     <th>在库数量</th>
							     <th>借出数量</th>
							 	<th>内容简介</th>
							 	<th>可借状态</th>
							</thead>
						<tbody class="tablebook3">
								<%
								if((session.getAttribute("user_id"))!=null){
								List <Book> books =new AdminDao().queryBooks();
								  for(Book book:books){
								%>
							 <tr>
							 	<td><input type="checkbox" name="" value="33"></td>
							 	<td><input type="text" class="bid botext" name="id" value="<%=book.getBook_id() %>" size=5 style="border:0px;text-align:center;" readonly="readonly"></td>
								<td><input type="text" class="bname botext" name="name" value="<%=book.getBook_name() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bISBN botext" name="ISBN" value="<%=book.getBook_ISBN() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bauthor botext" name="author" value="<%=book.getBook_author() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="btype botext" name="type" value="<%=book.getBook_type()%>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bpress botext" name="press" value="<%=book.getBook_press() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bprice botext" name="price" value="<%=book.getBook_price() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bnum botext" name="num" value="<%=book.getBook_num() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bbonum botext" name="bonum" value="<%=book.getBook_bonum() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bcontent botext" name="content" value="<%=book.getBook_content() %>" size=5 style="border:0px;text-align:center;"></td>
								<td><input type="text" class="bprice botext" name="state" value="<%=book.getBook_state() %>" size=5 style="border:0px;text-align:center;"></td>
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
			 				 <ul class="pagination" id="table_bmanage_ad">
			 				 </ul>
						</nav>
 					<!-- 	<form name="ResultForm" id="uform">		
							<input type="hidden" name="set" value="" class="UpdateForm">
						</form>  -->
						 <input type="hidden" name="set" value="" class="UpdateForm">
 						<form action="servlet/DeleteServlet" method="post" name="ResultForm1">
							<input type="hidden" name="set" value="" class="DeleteForm">
						</form>	 
					  </div>
					</div>
		      </div>
	     </div>
      </div>
      
      
      	<!-- 批量导入图书信息的表单 -->
      <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">批量新增图书</h4>
          </div>
          <div class="modal-body">
			  <form action="servlet/ImportBookServlet" method="post" enctype="multipart/form-data" name="booksinsertform">
					<p>上传文件<input type="file" name="uploadfile"></p>
					<!-- <p><input type="submit" name="import" value="批量导入"></p> -->
			 </form>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
            <button class="btn btn-primary" type="button" onclick="insertbooks()">批量导入</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
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
         
            <table class="table table-bordered" style="text-align:center;">
			  <tbody>
			     <tr>
			        <td>图书编号</td> 
			        <td><input type="text" name="book_id" value="" class="book_id"></td> 
			     </tr>
			 	<tr>
			        <td>图书标准码</td> 
			        <td><input type="text" name="book_ISBN" value="" class="book_ISBN"></td> 
			     </tr>
			 	<tr>
			        <td>书名</td> 
			        <td><input type="text" name="book_name" value="" class="book_name"></td> 
			    </tr>
			 	<tr>
			        <td>作者</td> 
			        <td><input type="text" name="book_author" value="" class="book_author"></td> 
			    </tr>
			 	<tr>
			        <td>图书类型</td> 
			        <td>
			  		    <select class="form-control book_type" name="book_type" style="width:50%;margin:0 auto;">
						   <option value="1">马列主义毛泽东思想</option>
						   <option value="2">哲学</option>
						   <option value="3">社会科学</option>
						   <option value="4">自然科学</option>
						   <option value="5">综合</option>
						</select>
			        </td> 
			    </tr>
			    <tr>
			        <td>出版社</td> 
			        <td><input type="text" name="book_press" value="" class="book_press"></td> 
			    </tr>
			    <tr>
			        <td>定价</td> 
			        <td><input type="text" name="book_price" value="" class="book_price"></td> 
			    </tr>
			    <tr>
			        <td>入库数量</td> 
			        <td><input type="text" name="book_runum" value="" class="book_runum"></td> 
			    </tr>
			    <tr>
			        <td>内容简介</td> 
			        <td><input type="text" name="book_content" value="" class="book_content"></td> 
			    </tr>
			     <tr>
			    	<td>图书图片</td> 
			        <td> 
				        <form action="servlet/UploadServlet" method="post" name="ResultForm2" enctype="multipart/form-data">
				        <!-- <input type="file" name="uploadFile" class="inputimg"> -->
				        <input type="file" name="uploadFile" class="inputimg">
				        </form>
			        </td> 
			    </tr>
			    
			  </tbody>
			</table>
			
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
<!--  	<form action="servlet/ImportStuServlet" method="post" enctype="multipart/form-data">
		<p>上传文件<input type="file" name="uploadfile"></p>
		<p><input type="submit" name="import" value="批量导入"></p>
	</form> --> 
	
<script type="text/javascript" src="js/index.js"></script>
		<script type="text/javascript">
		tableonPage_bmange_ad(1,8);
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
				$('.nav5').hide();
				$(".nav6").hide();
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
				$('.nav5').show();
				$(".nav6").show();
				$('#stu_br_state').hide();
				$('#admin_br_state').show();
				if($('#utype').val()=="1"){
					
				}else if($('#utype').val()=="1"){
					
				}
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
				$('.nav5').show();
				$(".nav6").show();
				$('#stu_br_state').hide();
				$('#admin_br_state').show();
				if($('#utype').val()=="1"){
					
				}else if($('#utype').val()=="1"){
					
				}
			}
		}
		}; 
	</script>
</body>
</html>