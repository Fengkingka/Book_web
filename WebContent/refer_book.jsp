<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>图书借记系统——借阅图书</title>
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
			<ul class="nav navbar-nav" id="navchange">
				<li ><a href="index.jsp">首页</a></li>
				<li class="active"><a href="refer_book.jsp">查阅图书</a></li>
				<li style="display:none;" class="nav3"><a href="borrow_state.jsp">借记情况</a></li>
				<li style="display:none;" class="nav4"><a href="user_manage.jsp">用户管理</a></li>
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
	<div class="container search-small">
		<div class="input-group">
	      <input type="text" class="form-control" placeholder="书名/类型/ISBN/出版社">
	      <span class="input-group-btn">
	        <button class="btn btn-default" type="button" onclick="searchbook('form-control')">搜索</button>
	      </span>
	    </div>
    </div>
<div class="container" id="bookinfo">
 <div class="row book_block">
  <div class="col-xs-2 " >
<!--     <a href="#" class="thumbnail">
      <img src="images/1900743504_ii_cover.jpg" width="150">
    </a>
	<div class="caption">
        
      </div> -->
  </div>
  <div class="col-xs-10">
	<table class="table table-hover">
	<tbody>
<!-- 		<tr>
			<td width="6%">书名:</td>
			<td class="bname" width="10%">简单的逻辑学</td>
			<td width="10%">ISBN:</td>
			<td width="8%">9787538759907</td>
			<td>内容简介:<td>
		</tr>
		<tr>
			<td>作者:</td>
			<td>D.Q麦克伦尼</td>
			<td>图书类型:</td>
			<td>哲学</td>
			<td rowspan="2">这是一本足以彻底改变你思维世界的小书。正如著名行为学家孙路弘所说：缺乏逻辑已成为社会的一种流行病症：逻辑紊乱症候群。而《简单的逻辑学》就如一场及时雨，一本治愈社会疾病的宝典，的确是应该人手一册。</td>
		</tr>
		<tr>
			<td>出版社:</td>
			<td>浙江人民出版社</td>
			<td>定价:</td>
			<td>18</td>
		</tr>
		<tr>
			<td><a href="#" class="btn btn-primary btnbo" role="button">借阅</a></td>
			<td><a href="#" class="btn btn-primary btnre" role="button">归还</a></td>
			<td><a href="#" class="btn btn-primary btnxu" role="button">续借</a></td>
			<td></td>
		</tr> -->
	</tbody>
	</table>
  </div>
</div> 
	<input type="hidden" value="" class="sebook_id">
</div>
<div class="container">
	<div class="row">
		<div class="page col-xs-3">
			<nav>
			  <ul class="pagination" id="book_page">
<!--  			    <li>
			      <a href="#" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
 			    <li><a href="#">1</a></li>
			    <li><a href="#">2</a></li>
			    <li><a href="#">3</a></li>
			    <li><a href="#">4</a></li>
			    <li><a href="#">5</a></li>
			    <li>
			      <a href="#" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>  -->
			  </ul>
			</nav>
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
				$('#stu_br_state').hide();
				$('#admin_br_state').show();
				
			}
			
		}
		}; 
	</script>
	<%
		String book_name=request.getParameter("book_name");
	//	System.out.println(book_name);
		if(book_name!=null){
			System.out.println(book_name);
	%>	
	<script type="text/javascript">
			onshowbook("<%=book_name%>");
	</script>
	<%
		}
		
	%>
	
</body>
</html>