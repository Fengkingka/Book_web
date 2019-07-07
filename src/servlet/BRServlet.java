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

@WebServlet("/BRServlet")
public class BRServlet extends HttpServlet{
	 public BRServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			//获取图书编号
			String book_id =request.getParameter("set");
			//获取执行哪个操作，借阅、归还、续借
			String brxbook =request.getParameter("how");
			System.out.print(brxbook);
			//获取用户的类型
			String user_type=request.getParameter("user_type");
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
			String user_id=request.getParameter("user_id");;
			//借还状态,1为在借，0为已还
			String br_state1= "1";
			String br_state0= "0";
			//借阅、归还、续借是否成功
			boolean flag1 = false;
			boolean flag2 = false;
			boolean flag3 = false;
			//学生是否为黑名单
			boolean flag4 =false;
			PrintWriter out = response.getWriter();
				try {
					//先查询学生是否被列入黑名单,true为黑名单
					flag4=new StudentDao().check_stu_black(user_id);
					if (flag4) {
						System.out.println("不可借阅,您被列入黑名单");
						out.println("false41");
					}else {
						if (brxbook.equals("bobook")) {
							//如果是学生
							if(!user_type.equals("2")) {
								//查询该学生是否达成可借书数上限
								if(new StudentDao().check_stu_bonum(user_id)) {
									System.out.println("不可借阅,已达可借上限");
									out.println("false51");
								}else {
									//先查询该图书是否可借(即在库数量是否为0)
									//然后查询该用户是否有借还过该图书
									//再检查该图书的借还状态
									boolean flag11=false;
									flag11=new StudentDao().check_bnum(book_id);
									if (!flag11) {
										System.out.println("该图书不可借，在库数量为0，已全部借出");
										//将图书状态改为不可借
										if(new StudentDao().update_bstate(book_id)) {
											out.println("false13");
										}
									}else {
										String flag="";
										flag =new StudentDao().check_bstate(user_id, book_id);
										if(flag.equals("true")) {
											System.out.println("该图书在借中");
											out.println("false1");
										}else if (flag.equals("false")) {
											flag1 = new StudentDao().update_bstate(book_id, user_id, b_date, s_r_date,"",br_state1);
											boolean flag12=false;
											flag12 = new StudentDao().update_bnum_down(book_id);
											if (flag1&&flag12) {
												//图书被借阅次数加一
												//学生借书证历史借阅书数、当前在借书数加一
												if(user_type.equals("2")) {
													if(new StudentDao().update_hisnum(book_id)) {
														System.out.println("借阅图书成功");
														out.println("true1");
													}
												}
												else if (new StudentDao().update_hisnum(book_id)&&new StudentDao().update_bookper_up(user_id)) {
													System.out.println("借阅图书成功");
													out.println("true1");
												}
											}else {
												System.out.println("借阅图书失败，借阅sql语句1出错或者改变数量sql语句出错");
												out.println("false11");
											}
										}else {
											flag1 = new StudentDao().borrowBook(book_id,b_date,s_r_date,user_id,br_state1);
											boolean flag13=false;
											flag13 =new StudentDao().update_bnum_down(book_id);
											if (flag1) {
												//图片被借阅次数加一
												//学生借书证历史借阅书数、当前在借书数加一
												if(user_type.equals("2")) {
													if(new StudentDao().update_hisnum(book_id)) {
														System.out.println("借阅图书成功");
														out.println("true1");
													}
												}
												else if (new StudentDao().update_hisnum(book_id)&&new StudentDao().update_bookper_up(user_id)) {
													System.out.println("借阅图书成功");
													out.println("true1");
												}
											}else {
												System.out.println("借阅图书失败，借阅sql语句2出错");
												out.println("false12");
											}
										}	
									}
								}
							}
							//如果是超级管理员
							else {
								//先查询该图书是否可借(即在库数量是否为0)
								//然后查询该用户是否有借还过该图书
								//再检查该图书的借还状态
								boolean flag11=false;
								flag11=new StudentDao().check_bnum(book_id);
								if (!flag11) {
									System.out.println("该图书不可借，在库数量为0，已全部借出");
									//将图书状态改为不可借
									if(new StudentDao().update_bstate(book_id)) {
										out.println("false13");
									}
								}else {
									String flag="";
									flag =new StudentDao().check_bstate(user_id, book_id);
									if(flag.equals("true")) {
										System.out.println("该图书在借中");
										out.println("false1");
									}else if (flag.equals("false")) {
										flag1 = new StudentDao().update_bstate(book_id, user_id, b_date, s_r_date,"",br_state1);
										boolean flag12=false;
										flag12 = new StudentDao().update_bnum_down(book_id);
										if (flag1&&flag12) {
											//图书被借阅次数加一
											//学生借书证历史借阅书数、当前在借书数加一
											if(user_type.equals("2")) {
												if(new StudentDao().update_hisnum(book_id)) {
													System.out.println("借阅图书成功");
													out.println("true1");
												}
											}
											else if (new StudentDao().update_hisnum(book_id)&&new StudentDao().update_bookper_up(user_id)) {
												System.out.println("借阅图书成功");
												out.println("true1");
											}
										}else {
											System.out.println("借阅图书失败，借阅sql语句1出错或者改变数量sql语句出错");
											out.println("false11");
										}
									}else {
										flag1 = new StudentDao().borrowBook(book_id,b_date,s_r_date,user_id,br_state1);
										boolean flag13=false;
										flag13 =new StudentDao().update_bnum_down(book_id);
										if (flag1) {
											//图片被借阅次数加一
											//学生借书证历史借阅书数、当前在借书数加一
											if(user_type.equals("2")) {
												if(new StudentDao().update_hisnum(book_id)) {
													System.out.println("借阅图书成功");
													out.println("true1");
												}
											}
											else if (new StudentDao().update_hisnum(book_id)&&new StudentDao().update_bookper_up(user_id)) {
												System.out.println("借阅图书成功");
												out.println("true1");
											}
										}else {
											System.out.println("借阅图书失败，借阅sql语句2出错");
											out.println("false12");
										}
									}	
								}
							}

						}else if(brxbook.equals("rebook")) {
							//先查询该用户是否有借还过该图书
							//然后查询是否该图书处于在借状态
							//true为在借，false为已归还
							String flag="";
							flag =new StudentDao().check_bstate(user_id, book_id);
							if (flag.equals("true")) {
								flag2 = new StudentDao().returnBook(book_id,b_date,user_id, br_state0);
								boolean flag21=false;
								flag21 =new StudentDao().update_bnum_up(book_id);
								if(flag2&&flag21){
									//将图片改为可借状态1
									//将学生借书证的当前在借书数减一
									if(new StudentDao().update_bstate1(book_id)&&new StudentDao().update_bookper_down(user_id)) {
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
								System.out.println("您没有借阅过该图书");
								out.println("false22");							
							}
						}else {
							//查询该用户是否借阅过该图书
							//再检查该图书的借还状态
							String flag="";
							flag = new StudentDao().check_s_r_date(user_id, book_id);
							if(flag.equals("false")) {
								System.out.println("该图书已归还");
								out.println("false31");							
							}else if (flag.equals("false1")) {
								System.out.println("续借图书失败，查询sql语句出错");
								out.println("false32");
							}else if (flag.equals("none")) {
								System.out.println("您没有借阅过该图书");
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
								flag3 = new StudentDao().renewBook(book_id, flag, user_id);
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
