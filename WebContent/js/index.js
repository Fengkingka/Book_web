//首页的图书展示
$(function () {
//	window.location.href="";
	var user_id = $("#uid").val();
	 $.ajax({
	        type: 'POST',
	        url:'servlet/ShowBookServlet',
	       // url: ' http://172.20.110.102:3308/app/Web_Exit_login',
	        dataType: "json",
	    	data:{"user_id":user_id,
	    	},	
	        success: function(book){
	        	var data=JSON.stringify(book);
	        	var obj=JSON.parse(data);
	        //测试数据用	
	        //	alert(data);
	        	//图书信息的数量
	        	var bnum = obj.book_info.length;
	        	//存储创建新图书信息的标签
	        	var new_book;
	        	//热门图书
	        	for(var i=0;i<bnum;i++){
	        		var book_row =document.createElement('div');
	        		book_row.className="col-sm-3 hbook";
	        		new_book=null;
	        		//包含图片、书名信息
	        		new_book ="<img src=\""+obj.book_hotinfo[i].book_img+"\" height=\"170\" width=\"160\" /><a class=\"bname\" herf=\"#\" onclick=\"trychuan('"+obj.book_hotinfo[i].book_name+"')\">"+obj.book_hotinfo[i].book_name+"</a>";
	        		book_row.innerHTML=new_book;
	        		document.getElementById("hotbook").appendChild(book_row);
	        	}
	        	//新书上架
	        	for(var i=0;i<bnum;i++){
	        		var book_row =document.createElement('div');
	        		book_row.className="col-sm-3 nbook";
	        		new_book=null;
	        		//包含图片、书名信息
	        		new_book ="<img src=\""+obj.book_info[i].book_img+"\" height=\"170\" width=\"160\" /><a class=\"bname\" herf=\"#\" onclick=\"trychuan('"+obj.book_info[i].book_name+"')\">"+obj.book_info[i].book_name+"</a>";
	        		book_row.innerHTML=new_book;
	        		document.getElementById("newbook").appendChild(book_row);
	        	}
	        	//图书分类(没能实现)
	        //	alert(11);
	      //  	alert(obj.book_perinfo.length);
	        	//图书证的借记情况
	        	var bbnum =obj.book_perinfo[0].borrow_booknum-obj.book_perinfo[0].borrow_bookbonum;
	        	//可借书数
	        	$(".bo_booknum").text("当前可借书数："+bbnum+"本");
	        	$(".bo_bookbonum").text("当前在借书数："+obj.book_perinfo[0].borrow_bookbonum+"本");
	        	$(".bo_bookhisnum").text("历史借阅书数："+obj.book_perinfo[0].borrow_bookhisnum+"本");
	        },
	        error: function(XMLHttpRequest, textStatus, errorThrown) {
	            alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
	        },
	    });
});

//传页面尝试
function trychuan(bookname){
	$("#showbname").val(bookname);
	showdetilbook.submit();
}

//展示点击的图书
function onshowbook(bookname){
	$('#bookinfo .row').each(function(){
		$(this).remove();
	});
//	alert(bookname);
	$.ajax({
        url: 'servlet/SearchbnameServlet',
        type: 'POST',
        dataType: 'json',
        timeout: 1000,
        data:{"book_name":bookname},
        cache: false,
        beforeSend: LoadFunction, //加载执行方法  
        error: erryFunction,  //错误执行方法  
        success: succFunction //成功执行方法  
    })
    function LoadFunction() {
    }
    function erryFunction() {
        alert("error");
    }
    function succFunction(book) {
    	var data=JSON.stringify(book);
    	var obj=JSON.parse(data);
    //测试数据用	
   // 	alert(data);
    	
    	//图书信息的数量
    	var bnum = obj.book_info.length;
    	//存储创建新图书信息的标签
    	var new_book;
    	for(var i=0;i<bnum;i++){
    		var book_row =document.createElement('div');
    		book_row.className="row book_block";
    		new_book=null;
    		//生成col-xs-2，包含图片
    		new_book ="<div class=\"col-xs-2\" ><a href=\"#\" class=\"thumbnail\"><img src=\""+obj.book_info[i].book_img+"\"></a><div class=\"caption\"></div></div>";
    		//生成col-xs-10，包含书名、ISBN、类型、作者等信息和借阅、归还、续借的按钮
    		new_book +="<div class=\"col-xs-10\"><table class=\"table table-hover\"><tbody><tr><td width=\"6%\">书名:</td><td class=\"bname\" width=\"10%\">"+obj.book_info[i].book_name+"</td><td width=\"10%\">ISBN:</td><td width=\"8%\">"+obj.book_info[i].book_ISBN+"</td><td>内容简介:<td></tr>\<tr>\<td>作者:</td>\<td>"+obj.book_info[i].book_author+"</td>\<td>图书类型:</td>\<td>"+obj.book_info[i].book_type+"</td>\<td rowspan=\"2\">"+obj.book_info[i].book_content+"</td></tr><tr><td>出版社:</td><td>"+obj.book_info[i].book_press+"</td><td>定价:</td><td>"+obj.book_info[i].book_price+"</td></tr><tr><td><a href=\"#\" class=\"btn btn-primary btnbo\" role=\"button\">借阅</a></td><td><a href=\"#\" class=\"btn btn-primary btnre\" role=\"button\">归还</a></td><td><a href=\"#\" class=\"btn btn-primary btnxu\" role=\"button\">续借</a></td><td></td></tr><input type=\"hidden\" value=\""+obj.book_info[i].book_id+"\" class=\"idd\"></tbody></table></div>";
    		/*new_book +="<div class=\"bid\" style=\"display:none;\">"+obj.book_info[i].book_name+"</div>";*/
    		book_row.innerHTML=new_book;
    		document.getElementById("bookinfo").appendChild(book_row);
    	}
    	//点击借阅按钮触发事件
    	$('.btnbo').click(function(){
    		var a="";
    		a=$(this).parent().parent().parent().find('.idd').val();
    		$(".sebook_id").val(a);
    		//alert($(".sebook_id").val());
        	borrowbook("bobook");
    	});
    	//点击归还按钮触发事件
    	$('.btnre').click(function(){
    		var a="";
    		a=$(this).parent().parent().parent().find('.idd').val();
    		$(".sebook_id").val(a);
    		//alert($(".sebook_id").val());
        	borrowbook("rebook");
    	});
    	//点击续借按钮触发事件
    	$('.btnxu').click(function(){
    		var a="";
    		a=$(this).parent().parent().parent().find('.idd').val();
    		$(".sebook_id").val(a);
    		//alert($(".sebook_id").val());
        	borrowbook("xubook");
    	});
    	bookonPage(1,2);

    }
}


//用户登录
function login(){
	if(LoginForm.username.value==null||LoginForm.password.value==null){
		alert("账号或密码不能为空");
		return ;
	}else{
		LoginForm.submit();
	}
	
}
//图书显示
function book_se_show(number){
	var i=0;
	$(".bookitem").each(function(){
		if(i==number){
			$(this).show();
			i++;
		}else{
			$(this).hide();
			i++;
		}
	});
}

function showinfo(id){
	var btn=id;
	if(btn=="btn_bobook"){
		$('.side-bar a:eq(0)').addClass("active");
		$('.side-bar a:eq(1)').removeClass("active");
		$('.side-bar a:eq(2)').removeClass("active");
		$('.side-bar a:eq(3)').removeClass("active");
/*		$('.btn_rebook').removeClass("active");*/
		$(".bobookinfo").show();
		$(".rebookinfo").hide();
		$(".mybobookinfo").hide();
		$(".myrebookinfo").hide();
	}else if(btn=="btn_rebook"){
		$('.side-bar a:eq(1)').addClass("active");
		$('.side-bar a:eq(0)').removeClass("active");
		$('.side-bar a:eq(2)').removeClass("active");
		$('.side-bar a:eq(3)').removeClass("active");
		$(".bobookinfo").hide();
		$(".rebookinfo").show();
		$(".mybobookinfo").hide();
		$(".myrebookinfo").hide();
	}else if(btn=="btn_mybobook"){
		$('.side-bar a:eq(2)').addClass("active");
		$('.side-bar a:eq(0)').removeClass("active");
		$('.side-bar a:eq(1)').removeClass("active");
		$('.side-bar a:eq(3)').removeClass("active");
		$(".mybobookinfo").show();
		$(".myrebookinfo").hide();
		$(".bobookinfo").hide();
		$(".rebookinfo").hide();
	}else if(btn=="btn_myrebook"){
		$('.side-bar a:eq(3)').addClass("active");
		$('.side-bar a:eq(0)').removeClass("active");
		$('.side-bar a:eq(1)').removeClass("active");
		$('.side-bar a:eq(2)').removeClass("active");
		$(".mybobookinfo").hide();
		$(".myrebookinfo").show();
		$(".bobookinfo").hide();
		$(".rebookinfo").hide();
	}
	
}

function show_pwduser(id){
	var btn =id;
	if(btn=="btnpwd"){
		$('.side-bar a:eq(2)').addClass("active");
		$('.side-bar a:eq(0)').removeClass("active");
		$('.side-bar a:eq(1)').removeClass("active");
/*		$('.btn_rebook').removeClass("active");*/
		$("#pwdchange").show();
		$("#usermanage").hide();
		$("#adminmanage").hide();
	}else if(btn=="btnuser"){
		$('.side-bar a:eq(0)').addClass("active");
		$('.side-bar a:eq(1)').removeClass("active");
		$('.side-bar a:eq(2)').removeClass("active");
		$("#pwdchange").hide();
		$("#usermanage").show();
		$("#adminmanage").hide();
	}else{
		$('.side-bar a:eq(1)').addClass("active");
		$('.side-bar a:eq(2)').removeClass("active");
		$('.side-bar a:eq(0)').removeClass("active");
		$("#pwdchange").hide();
		$("#usermanage").hide();
		$("#adminmanage").show();
	}
}

//修改图书，获取图书信息的值
function updatebook(){
	var set = [];
	$('#ResultTable tbody tr').each(function() {
	    var row = [];
	    
	    $(this).find('td').find("input[type='text']").each(function() {
	        row.push($(this).val());
	    });
	    
	    set.push(row);
	});
	alert(set);
	$(".UpdateForm").val(set);
	//ResultForm.submit();
	 $.ajax({
	        type: 'POST',
	        url:'servlet/UpdateServlet',
	       // url: ' http://172.20.110.102:3308/app/Web_Exit_login',
	        dataType: "text",
	    	data:{"set":set+"",
	    	},	
	        success: function(data){
	        	var true1="true1",false1="false1";
	        	var a = eval("(" + data + ")");
	         	switch(a){
	    		case true1 :alert("修改图书信息成功");break;
	    		case false1 :alert("修改图书信息失败");break;
	    		default:
	    			alert("操作出错");
	         	}
	        },
	        error: function(XMLHttpRequest, textStatus, errorThrown) {
	            alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
	        },
	    });

}
//删除图书，获取图书编号的值
function deletebook(){
	var set=[];
	var i=0;
	$('#ResultTable tbody tr').each(function() {	    
	    $(this).find('td').find("input[type='checkbox']:checked").each(function() {
	    	set[i]=$(this).parent().parent().find('.bid').val();
	    	i++;
	    });
	});
	alert(set);
	$(".DeleteForm").val(set);
	ResultForm1.submit();
}
//新增图书
function insertbook(){
	//alert($(".inputimg").val());
//	var str=$(".inputimg").val();
	var file = "";
        var fileName = "";
        var fileExt = "";
       
            //获取文件的value值
            file = $(".inputimg").val()
            //获取文件名+扩展名
            fileName = file.split("\\").pop();
            //获取文件名
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
            //获取文件的扩展名
            fileExt = file.substr(file.indexOf("."));
            //清空DIV容器内容
            file=fileName+fileExt;
     //       alert(file);
      //      alert(fileName);
      //      alert(fileExt);
       
	//alert(11);
	$.ajax({
	        type: 'POST',
	        url:'servlet/InputServlet',
	       // url: ' http://172.20.110.102:3308/app/Web_Exit_login',
	        dataType: "text",
	    	data:{
	    		"book_id":$(".book_id").val(),
	    		"book_ISBN":$(".book_ISBN").val(),
	    		"book_name":$(".book_name").val(),
	    		"book_author":$(".book_author").val(),
	    		"book_type":$(".book_type").val(),
	    		"book_press":$(".book_press").val(),
	    		"book_price":$(".book_price").val(),
	    		"book_runum":$(".book_runum").val(),
	    		"book_content":$(".book_content").val(),
	    		"book_img":"images/"+file,
	    	},	
	        success: function(data){
	        	var true1="true1",false1="false1";
	        	var a = eval("(" + data + ")");
	         	switch(a){
	    		case true1 :alert("新增成功");window.location.reload();break;
	    		case false1 :alert("新增失败");break;
	    		default:
	    			alert("操作出错");
        	}
	        },
	        error: function(XMLHttpRequest, textStatus, errorThrown) {
	            alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
	        },
	    });
	ResultForm2.submit();
}
//新增单个学生用户
function insertuser(){
	//alert("1");
	if(userForm.user_id.value==""){
		alert("账号不能为空");
		return false;
	}else if(userForm.user_name.value==""){
		alert("密码不能为空");
		return false;
	}else{
		userForm.submit();
	}
}
//新增单个管理员用户
function insertadmin(){
	//alert("1");
	if(adminForm.user_id.value==""){
		alert("账号不能为空");
		return false;
	}else if(adminForm.user_name.value==""){
		alert("密码不能为空");
		return false;
	}else{
		adminForm.submit();
	}
}
//新增批量学生用户
function insertusers(){
	usersinsertform.submit();
}

//查阅图书，并且动态生成图书信息
function searchbook(id){
	var sbook =$('.'+id).val();
	$('#bookinfo .row').each(function(){
		$(this).remove();
	});
	$.ajax({
        url: 'servlet/SearchServlet',
        type: 'POST',
        dataType: 'json',
        timeout: 1000,
        data:{set:sbook},
        cache: false,
        beforeSend: LoadFunction, //加载执行方法  
        error: erryFunction,  //错误执行方法  
        success: succFunction //成功执行方法  
    })
    function LoadFunction() {
    }
    function erryFunction() {
        alert("error");
    }
    function succFunction(book) {
    	var data=JSON.stringify(book);
    	var obj=JSON.parse(data);
    //测试数据用	
   // 	alert(data);
    	
    	//图书信息的数量
    	var bnum = obj.book_info.length;
    	//存储创建新图书信息的标签
    	var new_book;
    	for(var i=0;i<bnum;i++){
    		var book_row =document.createElement('div');
    		book_row.className="row book_block";
    		new_book=null;
    		//生成col-xs-2，包含图片
    		new_book ="<div class=\"col-xs-2\" ><a href=\"#\" class=\"thumbnail\"><img src=\""+obj.book_info[i].book_img+"\"></a><div class=\"caption\"></div></div>";
    		//生成col-xs-10，包含书名、ISBN、类型、作者等信息和借阅、归还、续借的按钮
    		new_book +="<div class=\"col-xs-10\"><table class=\"table table-hover\"><tbody><tr><td width=\"6%\">书名:</td><td class=\"bname\" width=\"10%\">"+obj.book_info[i].book_name+"</td><td width=\"10%\">ISBN:</td><td width=\"8%\">"+obj.book_info[i].book_ISBN+"</td><td>内容简介:<td></tr>\<tr>\<td>作者:</td>\<td>"+obj.book_info[i].book_author+"</td>\<td>图书类型:</td>\<td>"+obj.book_info[i].book_type+"</td>\<td rowspan=\"2\">"+obj.book_info[i].book_content+"</td></tr><tr><td>出版社:</td><td>"+obj.book_info[i].book_press+"</td><td>定价:</td><td>"+obj.book_info[i].book_price+"</td></tr><tr><td><a href=\"#\" class=\"btn btn-primary btnbo\" role=\"button\">借阅</a></td><td><a href=\"#\" class=\"btn btn-primary btnre\" role=\"button\">归还</a></td><td><a href=\"#\" class=\"btn btn-primary btnxu\" role=\"button\">续借</a></td><td></td></tr><input type=\"hidden\" value=\""+obj.book_info[i].book_id+"\" class=\"idd\"></tbody></table></div>";
    		/*new_book +="<div class=\"bid\" style=\"display:none;\">"+obj.book_info[i].book_name+"</div>";*/
    		book_row.innerHTML=new_book;
    		document.getElementById("bookinfo").appendChild(book_row);
    	}
    	//如果是普通管理员或者游客，即不显示借阅、归还、续借的按钮
    	if($("#utype").val()=="0"||$("#utype").val()==""){
    		$('.btnbo').hide();
    		$('.btnre').hide();
    		$('.btnxu').hide();
    	}
    	//点击借阅按钮触发事件
    	$('.btnbo').click(function(){
    		var a="";
    		a=$(this).parent().parent().parent().find('.idd').val();
    		$(".sebook_id").val(a);
    		//alert($(".sebook_id").val());
        	borrowbook("bobook");
    	});
    	//点击归还按钮触发事件
    	$('.btnre').click(function(){
    		var a="";
    		a=$(this).parent().parent().parent().find('.idd').val();
    		$(".sebook_id").val(a);
    		//alert($(".sebook_id").val());
        	borrowbook("rebook");
    	});
    	//点击续借按钮触发事件
    	$('.btnxu').click(function(){
    		var a="";
    		a=$(this).parent().parent().parent().find('.idd').val();
    		$(".sebook_id").val(a);
    		//alert($(".sebook_id").val());
        	borrowbook("xubook");
    	});
    	bookonPage(1,2);

    }
}
//图书信息分页
//pno为页面，psize为每页显示的数量，num为所有图书的数目
function bookonPage(pno,psize){
	var num =$('#bookinfo .row').length;
	var totalPage=0; 	//总页数
	var pageSize = psize;	//每页显示列表数
	//总共分几页
	if((num/pageSize)>parseInt(num/pageSize)){
		totalPage=parseInt(num/pageSize)+1;
	}else{
		totalPage=parseInt(num/pageSize);
	}
	var currentPage = pno; //当前页数
	var startRow =(currentPage-1)*pageSize+1;   //开始显示的列表的列数
	var endRow =currentPage*pageSize;  //结束显示的列表的列数
	endRow =(endRow > num)? num : endRow;
	//遍历显示数据实现分页
			var i=0;
			$('#bookinfo .row').each(function(){
					i++;
				if(i>=startRow && i<=endRow){
					$(this).show();
				}else{
					$(this).hide();
				}
			});
			var tempStr="";
			if(currentPage>1){
				tempStr += "<a href=\"#\" onClick=\"bookonPage("+(1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" onClick=\"bookonPage("+(currentPage-1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">上页</a>"
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">上页</a>";;
			}
			if(currentPage<totalPage){
				tempStr += "<a href=\"#\" onClick=\"bookonPage("+(currentPage+1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" onClick=\"bookonPage("+(totalPage)+","+psize+")\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}
			tempStr+="<p style=\"text-align:center;\">共"+num+"条数据</p>";
		document.getElementById("book_page").innerHTML = tempStr;
}

//管理员查询到的表格分页--在借图书
function tableonPage_bo_ad(pno,psize){
	var num =$('.tablebook tr').length;
	var totalPage=0; 	//总页数
	var pageSize = psize;	//每页显示列表数
	//总共分几页
	if((num/pageSize)>parseInt(num/pageSize)){
		totalPage=parseInt(num/pageSize)+1;
	}else{
		totalPage=parseInt(num/pageSize);
	}
	var currentPage = pno; //当前页数
	var startRow =(currentPage-1)*pageSize+1;   //开始显示的列表的列数
	var endRow =currentPage*pageSize;  //结束显示的列表的列数
	endRow =(endRow > num)? num : endRow;
	//遍历显示数据实现分页
			var i=0;
			$('.tablebook tr').each(function(){
					i++;
				if(i>=startRow && i<=endRow){
					$(this).show();
				}else{
					$(this).hide();
				}
			});
			var tempStr="";
			if(currentPage>1){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_bo_ad("+(1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_bo_ad("+(currentPage-1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">上页</a>"
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">上页</a>";;
			}
			if(currentPage<totalPage){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_bo_ad("+(currentPage+1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_bo_ad("+(totalPage)+","+psize+")\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}
			tempStr+="<p>共"+num+"条数据</p>";
		document.getElementById("table_bo_ad").innerHTML = tempStr;
}

//管理员查询到的表格分页--已归还图书
function tableonPage_re_ad(pno,psize){
	var num =$('.tablebook2 tr').length;
	var totalPage=0; 	//总页数
	var pageSize = psize;	//每页显示列表数
	//总共分几页
	if((num/pageSize)>parseInt(num/pageSize)){
		totalPage=parseInt(num/pageSize)+1;
	}else{
		totalPage=parseInt(num/pageSize);
	}
	var currentPage = pno; //当前页数
	var startRow =(currentPage-1)*pageSize+1;   //开始显示的列表的列数
	var endRow =currentPage*pageSize;  //结束显示的列表的列数
	endRow =(endRow > num)? num : endRow;
	//遍历显示数据实现分页
			var i=0;
			$('.tablebook2 tr').each(function(){
					i++;
				if(i>=startRow && i<=endRow){
					$(this).show();
				}else{
					$(this).hide();
				}
			});
			var tempStr="";
			if(currentPage>1){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_re_ad("+(1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_re_ad("+(currentPage-1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">上页</a>"
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">上页</a>";;
			}
			if(currentPage<totalPage){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_re_ad("+(currentPage+1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_re_ad("+(totalPage)+","+psize+")\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}
			tempStr+="<p>共"+num+"条数据</p>";
		document.getElementById("table_re_ad").innerHTML = tempStr;
}

//超级管理员查询到的--我的在借图书
function tableonPage_mybo_ad(pno,psize){
	var num =$('.tablead_bobook tr').length;
	var totalPage=0; 	//总页数
	var pageSize = psize;	//每页显示列表数
	//总共分几页
	if((num/pageSize)>parseInt(num/pageSize)){
		totalPage=parseInt(num/pageSize)+1;
	}else{
		totalPage=parseInt(num/pageSize);
	}
	var currentPage = pno; //当前页数
	var startRow =(currentPage-1)*pageSize+1;   //开始显示的列表的列数
	var endRow =currentPage*pageSize;  //结束显示的列表的列数
	endRow =(endRow > num)? num : endRow;
	//遍历显示数据实现分页
			var i=0;
			$('.tablead_bobook tr').each(function(){
					i++;
				if(i>=startRow && i<=endRow){
					$(this).show();
				}else{
					$(this).hide();
				}
			});
			var tempStr="";
			if(currentPage>1){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_mybo_ad("+(1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_mybo_ad("+(currentPage-1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">上页</a>"
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">上页</a>";;
			}
			if(currentPage<totalPage){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_mybo_ad("+(currentPage+1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_mybo_ad("+(totalPage)+","+psize+")\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}
			tempStr+="<p>共"+num+"条数据</p>";
		document.getElementById("table_mybo").innerHTML = tempStr;
}


//超级管理员查询到的--我的归还图书
function tableonPage_myre_ad(pno,psize){
	var num =$('.tablead_rebook tr').length;
	var totalPage=0; 	//总页数
	var pageSize = psize;	//每页显示列表数
	//总共分几页
	if((num/pageSize)>parseInt(num/pageSize)){
		totalPage=parseInt(num/pageSize)+1;
	}else{
		totalPage=parseInt(num/pageSize);
	}
	var currentPage = pno; //当前页数
	var startRow =(currentPage-1)*pageSize+1;   //开始显示的列表的列数
	var endRow =currentPage*pageSize;  //结束显示的列表的列数
	endRow =(endRow > num)? num : endRow;
	//遍历显示数据实现分页
			var i=0;
			$('.tablead_rebook tr').each(function(){
					i++;
				if(i>=startRow && i<=endRow){
					$(this).show();
				}else{
					$(this).hide();
				}
			});
			var tempStr="";
			if(currentPage>1){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_myre_ad("+(1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_myre_ad("+(currentPage-1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">上页</a>"
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">上页</a>";;
			}
			if(currentPage<totalPage){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_myre_ad("+(currentPage+1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_myre_ad("+(totalPage)+","+psize+")\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}
			tempStr+="<p>共"+num+"条数据</p>";
		document.getElementById("table_myre").innerHTML = tempStr;
}




//管理员查询到的图书管理表格分页
function tableonPage_bmange_ad(pno,psize){
	var num =$('.tablebook3 tr').length;
	var totalPage=0; 	//总页数
	var pageSize = psize;	//每页显示列表数
	//总共分几页
	if((num/pageSize)>parseInt(num/pageSize)){
		totalPage=parseInt(num/pageSize)+1;
	}else{
		totalPage=parseInt(num/pageSize);
	}
	var currentPage = pno; //当前页数
	var startRow =(currentPage-1)*pageSize+1;   //开始显示的列表的列数
	var endRow =currentPage*pageSize;  //结束显示的列表的列数
	endRow =(endRow > num)? num : endRow;
	//遍历显示数据实现分页
			var i=0;
			$('.tablebook3 tr').each(function(){
					i++;
				if(i>=startRow && i<=endRow){
					$(this).show();
				}else{
					$(this).hide();
				}
			});
			var tempStr="";
			if(currentPage>1){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_bmange_ad("+(1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_bmange_ad("+(currentPage-1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">上页</a>"
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">上页</a>";;
			}
			if(currentPage<totalPage){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_bmange_ad("+(currentPage+1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_bmange_ad("+(totalPage)+","+psize+")\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}
			tempStr+="<p>共"+num+"条数据</p>";
		document.getElementById("table_bmanage_ad").innerHTML = tempStr;
}

//管理员查询到的学生用户管理表格分页
function tableonPage_umanage_ad(pno,psize){
	var num =$('.user_info tr').length;
	//alert(num);
	var totalPage=0; 	//总页数
	var pageSize = psize;	//每页显示列表数
	//总共分几页
	if((num/pageSize)>parseInt(num/pageSize)){
		totalPage=parseInt(num/pageSize)+1;
	}else{
		totalPage=parseInt(num/pageSize);
	}
	var currentPage = pno; //当前页数
	var startRow =(currentPage-1)*pageSize+1;   //开始显示的列表的列数
	var endRow =currentPage*pageSize;  //结束显示的列表的列数
	endRow =(endRow > num)? num : endRow;
	//遍历显示数据实现分页
			var i=0;
			$('.user_info tr').each(function(){
					i++;
				if(i>=startRow && i<=endRow){
					$(this).show();
				}else{
					$(this).hide();
				}
			});
			var tempStr="";
			if(currentPage>1){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_umanage_ad("+(1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_umanage_ad("+(currentPage-1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">上页</a>"
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">上页</a>";;
			}
			if(currentPage<totalPage){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_umanage_ad("+(currentPage+1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_umanage_ad("+(totalPage)+","+psize+")\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}
			tempStr+="<p>共"+num+"条数据</p>";
		document.getElementById("table_umanage_ad").innerHTML = tempStr;
}

//管理员查询到的学生用户管理表格分页
function tableonPage_admanage_ad(pno,psize){
	var num =$('.admin_info tr').length;
	//alert(num);
	var totalPage=0; 	//总页数
	var pageSize = psize;	//每页显示列表数
	//总共分几页
	if((num/pageSize)>parseInt(num/pageSize)){
		totalPage=parseInt(num/pageSize)+1;
	}else{
		totalPage=parseInt(num/pageSize);
	}
	var currentPage = pno; //当前页数
	var startRow =(currentPage-1)*pageSize+1;   //开始显示的列表的列数
	var endRow =currentPage*pageSize;  //结束显示的列表的列数
	endRow =(endRow > num)? num : endRow;
	//遍历显示数据实现分页
			var i=0;
			$('.admin_info tr').each(function(){
					i++;
				if(i>=startRow && i<=endRow){
					$(this).show();
				}else{
					$(this).hide();
				}
			});
			var tempStr="";
			if(currentPage>1){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_admanage_ad("+(1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_admanage_ad("+(currentPage-1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">上页</a>"
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">上页</a>";;
			}
			if(currentPage<totalPage){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_admanage_ad("+(currentPage+1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_admanage_ad("+(totalPage)+","+psize+")\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}
			tempStr+="<p>共"+num+"条数据</p>";
		document.getElementById("table_admanage_ad").innerHTML = tempStr;
}



//学生查询到的在借图书表格分页
function tableonPage_bo_stu(pno,psize){
	var num =$('.tablestu_bobook tr').length;
	var totalPage=0; 	//总页数
	var pageSize = psize;	//每页显示列表数
	//总共分几页
	if((num/pageSize)>parseInt(num/pageSize)){
		totalPage=parseInt(num/pageSize)+1;
	}else{
		totalPage=parseInt(num/pageSize);
	}
	var currentPage = pno; //当前页数
	var startRow =(currentPage-1)*pageSize+1;   //开始显示的列表的列数
	var endRow =currentPage*pageSize;  //结束显示的列表的列数
	endRow =(endRow > num)? num : endRow;
	//遍历显示数据实现分页
			var i=0;
			$('.tablestu_bobook tr').each(function(){
					i++;
				if(i>=startRow && i<=endRow){
					$(this).show();
				}else{
					$(this).hide();
				}
			});
			var tempStr="";
			if(currentPage>1){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_bo_stu("+(1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_bo_stu("+(currentPage-1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">上页</a>"
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">上页</a>";;
			}
			if(currentPage<totalPage){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_bo_stu("+(currentPage+1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_bo_stu("+(totalPage)+","+psize+")\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}
			tempStr+="<p>共"+num+"条数据</p>";
		document.getElementById("table_bo_stu").innerHTML = tempStr;
}


//学生查询到的归还图书表格分页
function tableonPage_re_stu(pno,psize){
	var num =$('.tablestu_rebook tr').length;
	var totalPage=0; 	//总页数
	var pageSize = psize;	//每页显示列表数
	//总共分几页
	if((num/pageSize)>parseInt(num/pageSize)){
		totalPage=parseInt(num/pageSize)+1;
	}else{
		totalPage=parseInt(num/pageSize);
	}
	var currentPage = pno; //当前页数
	var startRow =(currentPage-1)*pageSize+1;   //开始显示的列表的列数
	var endRow =currentPage*pageSize;  //结束显示的列表的列数
	endRow =(endRow > num)? num : endRow;
	//遍历显示数据实现分页
			var i=0;
			$('.tablestu_rebook tr').each(function(){
					i++;
				if(i>=startRow && i<=endRow){
					$(this).show();
				}else{
					$(this).hide();
				}
			});
			var tempStr="";
			if(currentPage>1){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_re_stu("+(1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_re_stu("+(currentPage-1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">上页</a>"
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">首页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">上页</a>";;
			}
			if(currentPage<totalPage){
				tempStr += "<a href=\"#\" onClick=\"tableonPage_re_stu("+(currentPage+1)+","+psize+")\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" onClick=\"tableonPage_re_stu("+(totalPage)+","+psize+")\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}else{
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">下页</a>";
				tempStr += "<a href=\"#\" class=\"btn btn-default\" role=\"button\">尾页</a>";
			}
			tempStr+="<p>共"+num+"条数据</p>";
		document.getElementById("table_re_stu").innerHTML = tempStr;
}


//图书借阅
function borrowbook(brxbook){
	var bookid =$(".sebook_id").val();
	var user_id=$("#uid").val();
	var user_type=$("#utype").val();
//	alert(bookid);
//	alert(brxbook);
	$.ajax({
        url: 'servlet/BRServlet',
        type: 'POST',
        dataType: 'text',
        timeout: 1000,
        data:{"set":bookid,"how":brxbook,"user_id":user_id,"user_type":user_type},
        cache: false,
        beforeSend: LoadFunction, //加载执行方法  
        error: erryFunction,  //错误执行方法  
        success: succFunction //成功执行方法  
    })
    function LoadFunction() {
    }
    function erryFunction(e) {
        alert("error");
        console.log(e);
    }
    function succFunction(book) {
    	var true1="true1",true2="true2",true3="true3",false1="false1",false11="false11",false12="false12";
    	var false2="false2",false21="false21",false22="false22",false31="false31",false32="false32";
    	var false33="false33",false34="false34",false35="false35",false13="false13";
    	var false41="false41";
    	var false51="false51";
    	var a = eval("(" + book + ")");
    	//alert(a);
    //	console.log(book);
    	switch(a){
    		case true1 :alert("借阅图书成功");break;
    		case true2 :alert("归还图书成功");break;
    		case true3 :alert("续借图书成功");break;
    		case false1 :alert("该图书在借中");break;
    		case false11 :alert("借阅图书失败，借阅sql语句1出错或者改变数量sql语句出错");break;
    		case false12 :alert("借阅图书失败，借阅sql语句2出错");break;
    		case false13 :alert("该图书不可借，在库数量为0，已全部借出");break;
    		case false2 :alert("归还图书失败");break;
    		case false21 :alert("该图书已归还");break;
    		case false22 :alert("您没有借阅过该图书");break;
    		case false31 :alert("该图书已归还");break;
    		case false32 :alert("续借图书失败，查询sql语句出错");break;
    		case false33 :alert("您没有借阅过该图书");break;
    		case false34 :alert("续借图书失败，续借sql语句出错");break;
    		case false35 :alert("您已达续借次数，不能再续借图书");break;
    		case false41 :alert("您被列入黑名单，不可进行当前操作");break;
    		case false51 :alert("不可借阅，已达可借上限");break;
    		default:
    			alert("操作出错");
    	}

    }
}

//图书借阅,用于测试借阅按钮用
/*$('.btnbo').click(function(){
	var a="";
	a=$(this).parent().parent().parent().find('.idd').val();
	aler(a);
	$(".sebook_id").val(a);
	alert($(".sebook_id").val());
});*/
/*$(document).ready(function(){
	$(".btn").each(function(){*/
/*		$('.btn').live('click',(function(){
			var a="";
			a=$(this).parent().parent().parent().find('.bid').val();
			alert(a);
		});*/
/*	});
});*/

//用户退出
function userexit(){
	 $.ajax({
	        type: 'POST',
	        url:'servlet/ExitServlet',
	       // url: ' http://172.20.110.102:3308/app/Web_Exit_login',
	        dataType: "text",
	    	data:{
	    	},	
	        success: function(data){
	        	if(data=="true"){
	        		alert("退出成功");
	        		window.location.href='index.jsp';
	        	}
	        	else{
	        		alert("退出失败")
	        	}
	        },
	        error: function(XMLHttpRequest, textStatus, errorThrown) {
	            alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
	        },
	    }); 
}
//测试用
function test(){
/*	alert($('#uid').val());
	alert($('#utype').val());
	alert($('#uname').val())*/
	console.log($("#testtext").val()=="男")
}

//用户密码修改
function pwd_change(){
	var oldpwd =$("#oldpwd").val();
	var newpwd1 =$("#newpwd1").val();
	var newpwd2 =$("#newpwd2").val();
	var user_id =$("#uid").val();
	if(oldpwd==""){
		alert("请输入旧密码");
		return false;
	}else if(newpwd1==""){
		alert("请输入新密码");
		return false;
	}
	else if(newpwd2==""){
		alert("请再次输入新密码");
		return false;
	}else if(newpwd2!=newpwd1){
		alert("输入的两次新密码不一致");
		return false;
	}
	 $.ajax({
	        type: 'POST',
	        url:'servlet/PwdChangeServlet',
	       // url: ' http://172.20.110.102:3308/app/Web_Exit_login',
	        dataType:'text',
	    	data:{"user_id":user_id,"oldpwd":oldpwd,"newpwd":newpwd1,
	    	},	
	        success: function(data){
	        	var true1="true1",false1="false1",false2="false2";
	        	var a = eval("(" + data + ")");
	        	//alert(a);
	        	console.log(a);
	        	switch(a){
		    		case true1 :
		    			alert("修改密码成功");
		    			$("#oldpwd").val("");
		    			$("#newpwd1").val("");
		    			$("#newpwd2").val("");
		    			break;
		    		case false1 :alert("旧密码输入错误");break;
		    		case false2 :alert("新密码修改失败");break;
		    		default:
		    			alert("操作出错");
	        	}
	        },
	        error: function(XMLHttpRequest, textStatus, errorThrown) {
	            alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
	        },
	    }); 
}
//上传图片的组件
$(".myfile").fileinput({
    uploadUrl:"${APP_PATH}/news/uploadFile", //接受请求地址
    uploadAsync : true, //默认异步上传
    showUpload : false, //是否显示上传按钮,跟随文本框的那个
    showRemove : false, //显示移除按钮,跟随文本框的那个
    showCaption : true,//是否显示标题,就是那个文本框
    showPreview : true, //是否显示预览,不写默认为true
    dropZoneEnabled : false,//是否显示拖拽区域，默认不写为true，但是会占用很大区域
    //minImageWidth: 50, //图片的最小宽度
    //minImageHeight: 50,//图片的最小高度
    //maxImageWidth: 1000,//图片的最大宽度
    //maxImageHeight: 1000,//图片的最大高度
    //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
    //minFileCount: 0,
    maxFileCount : 1, //表示允许同时上传的最大文件个数
    enctype : 'multipart/form-data',
    validateInitialCount : true,
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
    msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    allowedFileTypes : [ 'image' ],//配置允许文件上传的类型
    allowedPreviewTypes : [ 'image' ],//配置所有的被预览文件类型
    allowedPreviewMimeTypes : [ 'jpg', 'png', 'gif' ],//控制被预览的所有mime类型
    language : 'zh'
})
//异步上传返回结果处理
$('.myfile').on('fileerror', function(event, data, msg) {
    console.log("fileerror");
    console.log(data);
});
//异步上传返回结果处理
$(".myfile").on("fileuploaded", function(event, data, previewId, index) {
    console.log("fileuploaded");
    var ref = $(this).attr("data-ref");
    $("input[name='" + ref + "']").val(data.response.url);

});

//上传前
$('.myfile').on('filepreupload', function(event, data, previewId, index) {
    console.log("filepreupload");
});




//重设用户的密码
function updateuserpwd(){
	var i=0;
	var sid="";
	$(".user_info tr").each(function(){
		if($(this).find(".uselect").is(':checked')){
			//获取账号id并且合并账号id
			if(i==0){
				sid+=$(this).find('.uid').val();
			} else{
				sid+=",";
				sid+=$(this).find('.uid').val();
				
			}  
		//	$(this).remove();
			//统计重设密码的账号数
			i++;
		}
	});
	if(sid==""){
		alert("请选择想要重设密码的账号")
	}else{
		//alert(sid);
		 $.ajax({
				
		        type: 'POST',
		        url:'servlet/UpdateuserpwdServlet',
		       // url: ' http://172.20.110.102:3308/app/Web_Delete_Sender',
		        dataType: "text",
		    	data:{
		    	"set" :sid,
		    	},
		        success: function(data){
		        	var true1="true1",false1="false1"
		        	var a = eval("(" + data + ")");
		         	switch(a){
		    		case true1 :alert("重设成功");break;
		    		case false1 :alert("重设失败");break;
		    		default:
		    			alert("操作出错");
	        	}
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		            alert(XMLHttpRequest.status);
		            alert(XMLHttpRequest.readyState);
		            alert(textStatus);
		        },
		    });
	}
}

//重设管理员的密码
function updateadminpwd(){
	var set=[];
	var i=0;
	$('.admin_info tr').each(function() {	    
	    $(this).find('td').find("input[type='checkbox']:checked").each(function() {
	    	set[i]=$(this).parent().parent().find('.uid').val();
	    	i++;
	    });
	});
//	alert(set);
	if(set==""){
		alert("请选择想要重设密码的管理员账号")
	}else{
		//alert(sid);
		 $.ajax({
		        type: 'POST',
		        url:'servlet/UpdateuserpwdServlet',
		       // url: ' http://172.20.110.102:3308/app/Web_Delete_Sender',
		        dataType: "text",
		    	data:{
		    	"set" :set+"",
		    	},
		        success: function(data){
		        	var true1="true1",false1="false1"
		        	var a = eval("(" + data + ")");
		         	switch(a){
		    		case true1 :alert("重设成功");break;
		    		case false1 :alert("重设失败");break;
		    		default:
		    			alert("操作出错");
	        	}
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		            alert(XMLHttpRequest.status);
		            alert(XMLHttpRequest.readyState);
		            alert(textStatus);
		        },
		    });
	}
}

//删除管理员的账号
function deleteadmins(){
	var set=[];
	var i=0;
	$('.admin_info tr').each(function() {	    
	    $(this).find('td').find("input[type='checkbox']:checked").each(function() {
	    	set[i]=$(this).parent().parent().find('.uid').val();
	    	i++;
	    });
	});
//	alert(set);
	if(set==""){
		alert("请选择想要删除的管理员账号")
	}else{
		//alert(sid);
		 $.ajax({
		        type: 'POST',
		        url:'servlet/DeleteuserServlet',
		       // url: ' http://172.20.110.102:3308/app/Web_Delete_Sender',
		        dataType: "text",
		    	data:{
		    	"set" :set+"",
		    	},
		        success: function(data){
		        	var true1="true1",false1="false1"
		        	var a = eval("(" + data + ")");
		         	switch(a){
		    		case true1 :alert("删除成功");break;
		    		case false1 :alert("删除失败");break;
		    		default:
		    			alert("操作出错");
		         	}
		         	window.location.href="admin_user_manage.jsp";
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		            alert(XMLHttpRequest.status);
		            alert(XMLHttpRequest.readyState);
		            alert(textStatus);
		        },
		    });
	}
}

//将用户移出移入黑名单
function updateuserflag(userflag){
	var i=0;
	var sid="";
	$(".user_info tr").each(function(){
		if($(this).find(".uselect").is(':checked')){
			//获取账号id并且合并账号id
			if(i==0){
				sid+=$(this).find('.uid').val();
			} else{
				sid+=",";
				sid+=$(this).find('.uid').val();
				
			}  
		//	$(this).remove();
			//统计重设密码的账号数
			i++;
		}
	});
	if(sid==""){
		alert("请选择想要列入或移出黑名单的账号")
	}else{
		//alert(sid);
		 $.ajax({
				
		        type: 'POST',
		        url:'servlet/BlackServlet',
		       // url: ' http://172.20.110.102:3308/app/Web_Delete_Sender',
		        dataType: "text",
		    	data:{
		    	"set" :sid,
		    	"userflag":userflag,
		    	},
		        success: function(data){
		        	var true1="true1",false1="false1",true2="true2",false2="false2";
		        	var a = eval("(" + data + ")");
		         	switch(a){
		    		case true1 :alert("列入黑名单成功");window.location.reload();break;
		    		case false1 :alert("列入黑名单失败");break;
		    		case true2 :alert("移出黑名单成功");window.location.reload();break;
		    		case false2 :alert("移出黑名单失败");break;
		    		default:
		    			alert("操作出错");
	        	}
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		            alert(XMLHttpRequest.status);
		            alert(XMLHttpRequest.readyState);
		            alert(textStatus);
		        },
		    });
	}
}

//删除学生用户
function deleteusers(){
	var i=0;
	var sid="";
	$(".user_info tr").each(function(){
		if($(this).find(".uselect").is(':checked')){
			//获取账号id并且合并账号id
			if(i==0){
				sid+=$(this).find('.uid').val();
			} else{
				sid+=",";
				sid+=$(this).find('.uid').val();
				
			}  
		//	$(this).remove();
			//统计重设密码的账号数
			i++;
		}
	});
	if(sid==""){
		alert("请选择想要删除的账号")
	}else{
		//alert(sid);
		 $.ajax({
				
		        type: 'POST',
		        url:'servlet/DeleteuserServlet',
		       // url: ' http://172.20.110.102:3308/app/Web_Delete_Sender',
		        dataType: "text",
		    	data:{
		    	"set" :sid,
		    	},
		        success: function(data){
		        	var true1="true1",false1="false1";
		        	var a = eval("(" + data + ")");
		         	switch(a){
		    		case true1 :alert("删除学生用户成功");window.location.reload();break;
		    		case false1 :alert("删除学生用户失败");break;
		    		default:
		    			alert("操作出错");
	        	}
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		            alert(XMLHttpRequest.status);
		            alert(XMLHttpRequest.readyState);
		            alert(textStatus);
		        },
		    });
	}
}

//批量新增图书的提交
function insertbooks(){
	booksinsertform.submit();
}

//学生在借记情况归还图书
function stu_rebook(){
	var user_id=$("#uid").val();
	var user_type=$("#utype").val();
	var set=[];
	var i=0;
	$('.tablestu_bobook tr').each(function() {	    
	    $(this).find('td').find("input[type='checkbox']:checked").each(function() {
	    	set[i]=$(this).parent().parent().find('.bid').val();
	    	i++;
	    });
	});
//	alert(set);
	if(set==""){
		alert("请选择想要归还的图书")
	}else{
		 $.ajax({
		        type: 'POST',
		        url:'servlet/Stu_reServlet',
		       // url: ' http://172.20.110.102:3308/app/Web_Exit_login',
		        dataType: "text",
		    	data:{"how":"rebook","set":set+"","user_id":user_id,"user_type":user_type,
		    	},	
		        success: function(book){
		          	var true1="true1",true2="true2",true3="true3",false1="false1",false11="false11",false12="false12";
		        	var false2="false2",false21="false21",false22="false22",false31="false31",false32="false32";
		        	var false33="false33",false34="false34",false35="false35",false13="false13";
		        	var false41="false41";
		        	var a = eval("(" + book + ")");
		        	//alert(a);
		        //	console.log(book);
		        	switch(a){
		        		case true1 :alert("借阅图书成功");break;
		        		case true2 :alert("归还图书成功");break;
		        		case true3 :alert("续借图书成功");break;
		        		case false1 :alert("该图书在借中");break;
		        		case false11 :alert("借阅图书失败，借阅sql语句1出错或者改变数量sql语句出错");break;
		        		case false12 :alert("借阅图书失败，借阅sql语句2出错");break;
		        		case false13 :alert("该图书不可借，在库数量为0，已全部借出");break;
		        		case false2 :alert("归还图书失败");break;
		        		case false21 :alert("该图书已归还");break;
		        		case false22 :alert("您没有借阅过该图书");break;
		        		case false31 :alert("该图书已归还");break;
		        		case false32 :alert("续借图书失败，查询sql语句出错");break;
		        		case false33 :alert("您没有借阅过该图书");break;
		        		case false34 :alert("续借图书失败，续借sql语句出错");break;
		        		case false35 :alert("您已达续借次数，不能再续借图书");break;
		        		case false41 :alert("您已被列入黑名单，不能执行当前操作");break;
		        		default:
		        			alert("操作出错");
		        	}
		        	window.location.href="borrow_state.jsp";
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		            alert(XMLHttpRequest.status);
		            alert(XMLHttpRequest.readyState);
		            alert(textStatus);
		        },
		    });
	}
	
}

//学生在借记情况续借图书
function stu_xubook(){
	var user_id=$("#uid").val();
	var set=[];
	var i=0;
	$('.tablestu_bobook tr').each(function() {	    
	    $(this).find('td').find("input[type='checkbox']:checked").each(function() {
	    	set[i]=$(this).parent().parent().find('.bid').val();
	    	i++;
	    });
	});
//	alert(set);
	if(set==""){
		alert("请选择想要续借的图书")
	}else{
		 $.ajax({
		        type: 'POST',
		        url:'servlet/Stu_reServlet',
		       // url: ' http://172.20.110.102:3308/app/Web_Exit_login',
		        dataType: "text",
		    	data:{"how":"xubook","set":set+"","user_id":user_id,
		    	},	
		        success: function(book){
		          	var true1="true1",true2="true2",true3="true3",false1="false1",false11="false11",false12="false12";
		        	var false2="false2",false21="false21",false22="false22",false31="false31",false32="false32";
		        	var false33="false33",false34="false34",false35="false35",false13="false13";
		        	var false41="false41";
		        	var a = eval("(" + book + ")");
		        	//alert(a);
		        //	console.log(book);
		        	switch(a){
		        		case true1 :alert("借阅图书成功");break;
		        		case true2 :alert("归还图书成功");break;
		        		case true3 :alert("续借图书成功");break;
		        		case false1 :alert("该图书在借中");break;
		        		case false11 :alert("借阅图书失败，借阅sql语句1出错或者改变数量sql语句出错");break;
		        		case false12 :alert("借阅图书失败，借阅sql语句2出错");break;
		        		case false13 :alert("该图书不可借，在库数量为0，已全部借出");break;
		        		case false2 :alert("归还图书失败");break;
		        		case false21 :alert("该图书已归还");break;
		        		case false22 :alert("您没有借阅过该图书");break;
		        		case false31 :alert("该图书已归还");break;
		        		case false32 :alert("续借图书失败，查询sql语句出错");break;
		        		case false33 :alert("您没有借阅过该图书");break;
		        		case false34 :alert("续借图书失败，续借sql语句出错");break;
		        		case false35 :alert("您已达续借次数，不能再续借图书");break;
		        		case false41 :alert("您已被列入黑名单，不能执行当前操作");break;
		        		default:
		        			alert("操作出错");
		        	}
		        	window.location.href="borrow_state.jsp";
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		            alert(XMLHttpRequest.status);
		            alert(XMLHttpRequest.readyState);
		            alert(textStatus);
		        },
		    });
	}
	
}

//超级管理员在借记情况归还图书
function admin_rebook(){
	var user_id=$("#uid").val();
	var user_type=$("#utype").val();
	var set=[];
	var i=0;
	$('.tablead_bobook tr').each(function() {	    
	    $(this).find('td').find("input[type='checkbox']:checked").each(function() {
	    	set[i]=$(this).parent().parent().find('.bid').val();
	    	i++;
	    });
	});
//	alert(set);
	if(set==""){
		alert("请选择想要归还的图书")
	}else{
		 $.ajax({
		        type: 'POST',
		        url:'servlet/Stu_reServlet',
		       // url: ' http://172.20.110.102:3308/app/Web_Exit_login',
		        dataType: "text",
		    	data:{"how":"rebook","set":set+"","user_id":user_id,"user_type":user_type,
		    	},	
		        success: function(book){
		          	var true1="true1",true2="true2",true3="true3",false1="false1",false11="false11",false12="false12";
		        	var false2="false2",false21="false21",false22="false22",false31="false31",false32="false32";
		        	var false33="false33",false34="false34",false35="false35",false13="false13";
		        	var false41="false41";
		        	var a = eval("(" + book + ")");
		        	//alert(a);
		        //	console.log(book);
		        	switch(a){
		        		case true1 :alert("借阅图书成功");break;
		        		case true2 :alert("归还图书成功");break;
		        		case true3 :alert("续借图书成功");break;
		        		case false1 :alert("该图书在借中");break;
		        		case false11 :alert("借阅图书失败，借阅sql语句1出错或者改变数量sql语句出错");break;
		        		case false12 :alert("借阅图书失败，借阅sql语句2出错");break;
		        		case false13 :alert("该图书不可借，在库数量为0，已全部借出");break;
		        		case false2 :alert("归还图书失败");break;
		        		case false21 :alert("该图书已归还");break;
		        		case false22 :alert("您没有借阅过该图书");break;
		        		case false31 :alert("该图书已归还");break;
		        		case false32 :alert("续借图书失败，查询sql语句出错");break;
		        		case false33 :alert("您没有借阅过该图书");break;
		        		case false34 :alert("续借图书失败，续借sql语句出错");break;
		        		case false35 :alert("您已达续借次数，不能再续借图书");break;
		        		case false41 :alert("您已被列入黑名单，不能执行当前操作");break;
		        		default:
		        			alert("操作出错");
		        	}
		        	window.location.href="borrow_state.jsp";
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		            alert(XMLHttpRequest.status);
		            alert(XMLHttpRequest.readyState);
		            alert(textStatus);
		        },
		    });
	}
	
}

//超级管理员在借记情况续借图书
function admin_xubook(){
	var user_id=$("#uid").val();
	var set=[];
	var i=0;
	$('.tablead_bobook tr').each(function() {	    
	    $(this).find('td').find("input[type='checkbox']:checked").each(function() {
	    	set[i]=$(this).parent().parent().find('.bid').val();
	    	i++;
	    });
	});
//	alert(set);
	if(set==""){
		alert("请选择想要续借的图书")
	}else{
		 $.ajax({
		        type: 'POST',
		        url:'servlet/Stu_reServlet',
		       // url: ' http://172.20.110.102:3308/app/Web_Exit_login',
		        dataType: "text",
		    	data:{"how":"xubook","set":set+"","user_id":user_id,
		    	},	
		        success: function(book){
		          	var true1="true1",true2="true2",true3="true3",false1="false1",false11="false11",false12="false12";
		        	var false2="false2",false21="false21",false22="false22",false31="false31",false32="false32";
		        	var false33="false33",false34="false34",false35="false35",false13="false13";
		        	var false41="false41";
		        	var a = eval("(" + book + ")");
		        	//alert(a);
		        //	console.log(book);
		        	switch(a){
		        		case true1 :alert("借阅图书成功");break;
		        		case true2 :alert("归还图书成功");break;
		        		case true3 :alert("续借图书成功");break;
		        		case false1 :alert("该图书在借中");break;
		        		case false11 :alert("借阅图书失败，借阅sql语句1出错或者改变数量sql语句出错");break;
		        		case false12 :alert("借阅图书失败，借阅sql语句2出错");break;
		        		case false13 :alert("该图书不可借，在库数量为0，已全部借出");break;
		        		case false2 :alert("归还图书失败");break;
		        		case false21 :alert("该图书已归还");break;
		        		case false22 :alert("您没有借阅过该图书");break;
		        		case false31 :alert("该图书已归还");break;
		        		case false32 :alert("续借图书失败，查询sql语句出错");break;
		        		case false33 :alert("您没有借阅过该图书");break;
		        		case false34 :alert("续借图书失败，续借sql语句出错");break;
		        		case false35 :alert("您已达续借次数，不能再续借图书");break;
		        		case false41 :alert("您已被列入黑名单，不能执行当前操作");break;
		        		default:
		        			alert("操作出错");
		        	}
		        	window.location.href="borrow_state.jsp";
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		            alert(XMLHttpRequest.status);
		            alert(XMLHttpRequest.readyState);
		            alert(textStatus);
		        },
		    });
	}
	
}

 