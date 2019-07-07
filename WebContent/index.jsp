<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>图书借记系统——首页</title>
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
				<li style="display:none;" class="nav3"><a href="borrow_state.jsp">借记情况</a></li>
				<li style="display:none;" class="nav5"><a href="book_manage.jsp">图书管理</a></li>
				<li style="display:none;" class="nav4"><a href="user_manage.jsp">用户管理</a></li>
				<li style="display:none;" class="nav6"><a href="admin_user_manage.jsp">用户管理</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li id="userlogin"><a href="login.jsp">登录</a></li>
				<li id="userexit" style="display:none;"><a href="#" onclick="userexit()">退出</a></li>
				<!-- <li><a href="#" onclick="test()">测试</a></li> -->
			</ul>
			<p class="navbar-text navbar-right weltext">欢迎您</p>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="headingOne">
					      <h4 class="panel-title">
					        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
					          借记细则
					        </a>
					      </h4>
					    </div>
					    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					      <div class="panel-body">
							<p>	1、每人限借5本图书</p>
							<p>	2、借期不超过30天</p>
							<p>	3、图书不得污损、撕毁、批注和丢失</p>
							<p>	4、图书若遗失，则按原价赔偿</p>
							<p>	5、在借图书只有续借一次，续借天数为10天</p>
					      </div>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="headingTwo">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
					          借记情况
					        </a>
					      </h4>
					    </div>
					    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
					      <div class="panel-body">
								<p class="bo_booknum"></p>
								<p class="bo_bookbonum"></p>
								<p class="bo_bookhisnum"></p>
					      </div>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="headingThree">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
					          查阅图书
					        </a>
					      </h4>
					    </div>
					    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
					      <div class="panel-body">
					       		<p>可查阅的图书类型：</p>
					       		<p>1、马列主义毛泽东思想</p>
					       		<p>2、哲学</p>
					       		<p>3、社会科学</p>
					       		<p>4、自然科学</p>
					       		<p>5、综合</p>
					      </div>
					    </div>
					  </div>
					</div>
			</div>
			<div class="col-sm-8">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item active">
							<a class="nav-link active" data-toggle="tab">热门图书  TOP4</a>
						</li>
					</ul>
			<div class="row" id="hotbook">
<!-- 				<div class="col-sm-3 hbook">
					<img src="images/img_1.jpg">
					<span class="bname">世界欠我一个你</span>
				</div>
				 -->
			</div>
						<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item active">
						<a class="nav-link active" data-toggle="tab">新书上架</a>
					</li>
				</ul>
			<div class="row" id="newbook">
<!-- 			<div class="col-sm-3 nbook">
				<img src="images/img_1.jpg">
				<span class="bname">世界欠我一个你</span>
			</div>
			-->
		</div>
			</div>
		</div>

		<div class="row jiange" style="display:none;">
			<div class="col-sm-12">
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item active">
						<a class="nav-link active" data-toggle="tab" onclick="book_se_show(0)">马列主义毛泽东思想</a>
					</li>
					<li class="nav-item ">
						<a class="nav-link" data-toggle="tab" onclick="book_se_show(1)">哲学</a>
					</li>
					<li class="nav-item ">
						<a class="nav-link" data-toggle="tab" onclick="book_se_show(2)">社会科学</a>
					</li>
					<li class="nav-item ">
						<a class="nav-link" data-toggle="tab" onclick="book_se_show(3)">自然科学</a>
					</li>
					<li class="nav-item ">
						<a class="nav-link" data-toggle="tab" onclick="book_se_show(4)">综合</a>
					</li>
				</ul>

			</div>
		</div>
		<div class="row bookitem " style="" id="bookitem1">
			<!-- <div class="col-sm-2 book">
				<img src="images/img_2.jpg">
				<span class="bname">世界欠我一个你</span>
			</div> -->
		</div>
		<div class="row bookitem " style="display: none;" id="bookitem2">
			<!-- <div class="col-sm-2 book">
				<img src="images/img_2.jpg">
				<span class="bname">周期</span>
			</div> -->
		</div>
		<div class="row bookitem " style="display: none;" id="bookitem3">
			<!-- <div class="col-sm-2 book">
				<img src="images/img_2.jpg">
				<span class="bname">柠檬心理课堂</span>
			</div> -->
		</div>
		<div class="row bookitem " style="display: none;" id="bookitem4">
			<!-- <div class="col-sm-2 book">
				<img src="images/img_2.jpg">
				<span class="bname">财务自由之路2</span>
			</div> -->
		</div>
		<div class="row bookitem " style="display: none;" id="bookitem5">
			<!-- <div class="col-sm-2 book">
				<img src="images/img_2.jpg">
				<span class="bname">世界欠我一个你</span>
			</div> -->
		</div>
	</div>
		<input type="hidden" id="uid" value="<%=session.getAttribute("user_id")%>">
		<input type="hidden" id="uname" value="<%=session.getAttribute("user_name")%>">
		<input type="hidden" id="utype" value="<%=session.getAttribute("user_type")%>">
		<input type="hidden" id="uflag" value="<%=session.getAttribute("user_flag")%>">
		<form action="refer_book.jsp" name="showdetilbook" method="get">
			<input type="hidden" id="showbname" value="" name="book_name">
		</form>
		<!-- <input type="text" id="testtext" value="男"> -->
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
				$("#userlogin").hide();
				$("#userexit").show();
				$('.nav3').show();
				$('.nav4').show();
				$('.nav5').hide();
				
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
				$('.nav4').hide();
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