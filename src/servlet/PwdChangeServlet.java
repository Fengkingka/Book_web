package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;

@WebServlet("/PwdChangeServlet")
public class PwdChangeServlet extends HttpServlet {
	
	 public PwdChangeServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String user_id =request.getParameter("user_id");
			String oldpwd =request.getParameter("oldpwd");
			String newpwd =request.getParameter("newpwd");
			ResultSet rs;
			//得到有多少条数据
			boolean flag = false;
				try {
					rs = new StudentDao().user_login(user_id,oldpwd);
					if (rs.next()) {
						flag = new StudentDao().pwdchang(user_id,newpwd);
						if (flag) {
							//新密码修改成功
							out.println("true1");
							System.out.println("新密码修改成功");
						}else {
							//新密码修改失败
							out.println("false2");
							System.out.println("新密码修改失败");
						}
					}else {
						//旧密码输入错误
						out.println("false1");
						System.out.println("旧密码输入错误");
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
