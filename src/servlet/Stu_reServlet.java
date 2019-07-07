package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import dao.StudentDao;

@WebServlet("/Stu_reServlet")
public class Stu_reServlet extends HttpServlet{
	 public Stu_reServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			//获取图书编号
			String bookid =request.getParameter("set");
			String []book_id=bookid.split(",");
			for(int i=0;i<book_id.length;i++) {
				System.out.println(book_id[i]);
			}
			//获取执行哪个操作，借阅、归还、续借
			String brxbook =request.getParameter("how");
			System.out.print(brxbook);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			Calendar ca = Calendar.getInstance();
	        ca.add(Calendar.DATE, 30);
	        //当前日期
			String b_date=df.format(new Date());// new Date()为获取当前系统时间
			//应归还日期
			String s_r_date =df.format(ca.getTime());
			//续借时的应归还日期
			String s_r_date1 =null;
			//借书人id
			String user_id=request.getParameter("user_id");
			//借书人类型
			String user_type=request.getParameter("user_type");
			//借还状态,1为在借，0为已还
			String br_state1= "1";
			String br_state0= "0";
			//借阅、归还、续借是否成功
			boolean flag1 = false;
			boolean flag2 = false;
			boolean flag3 = false;
			boolean flag4 = false;
			PrintWriter out = response.getWriter();
				try {
					//先查询当前学生是否被列入黑名单
					flag4=new StudentDao().check_stu_black(user_id);
					if(flag4) {
						out.println("false41");
					}else {
						for(int i=0;i<book_id.length;i++) {
							if(brxbook.equals("rebook")) {
								//先查询该用户是否有借还过该图书
								//然后查询是否该图书处于在借状态
								//true为在借，false为已归还
								String flag="";
								flag =new StudentDao().check_bstate(user_id, book_id[i]);
								if (flag.equals("true")) {
									flag2 = new StudentDao().returnBook(book_id[i],b_date,user_id, br_state0);
									boolean flag21=false;
									flag21 =new StudentDao().update_bnum_up(book_id[i]);
									if(flag2&&flag21){
										//将图书改为可借状态1
										//将学生借书证的当前在借书数减一
										if(user_type.equals("2")) {
											if(new StudentDao().update_bstate1(book_id[i])) {
												System.out.println("归还图书成功");
												out.println("true2");
											}
										}
										else if(new StudentDao().update_bstate1(book_id[i])&&new StudentDao().update_bookper_down(user_id)) {
											System.out.println("归还图书成功");
											out.println("true2");
										}
									}else {
										System.out.println("归还图书失败");
										out.println("false2");
									}
								}else if (flag.equals("false")) {
									System.out.println("该图书已归还");
									out.println("false21");							
								}else {
									System.out.println("您没有借阅过该图书1");
									out.println("false22");							
								}
							}else {
								//查询该用户是否借阅过该图书
								//再检查该图书的借还状态
								String flag="";
								flag = new StudentDao().check_s_r_date(user_id, book_id[i]);
								if(flag.equals("false")) {
									System.out.println("该图书已归还");
									out.println("false31");							
								}else if (flag.equals("false1")) {
									System.out.println("续借图书失败，查询sql语句出错");
									out.println("false32");
								}else if (flag.equals("none")) {
									System.out.println("您没有借阅过该图书2");
									out.println("false33");	
								}else if (flag.equals("false2")) {
									System.out.println("您已达续借次数，不能再续借图书");
									out.println("false35");
								}
								else {
									Date date1 = df.parse(flag);
									ca.setTime(date1);//设置到Calendar里
									ca.add(Calendar.DATE, 10);
									flag=df.format(ca.getTime());
									flag3 = new StudentDao().renewBook(book_id[i], flag, user_id);
									if(flag3){
										System.out.println("续借图书成功");
										out.println("true3");
									}else {
										System.out.println("续借图书失败，续借sql语句出错");
										out.println("false34");
									}
								}	
							}
						}
					}
		
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
	             out.flush();
	             out.close();
			}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
}
