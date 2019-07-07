package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.User;
import dao.AdminDao;
import dao.StudentDao;

@WebServlet("/InsertadminServlet")
public class InsertadminServlet extends HttpServlet{
	 public InsertadminServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			String user_id =request.getParameter("user_id");
			String user_name =request.getParameter("user_name");
		//	System.out.println(user_id);
		//	System.out.println(user_name);
			String user_pwd ="888888";
			String user_type="0";
			String user_flag="1";
			User user =new User(user_id, user_name, user_pwd, user_type, user_flag);
			boolean flag = false;
			boolean flag1= false;
				try {
						
					flag = new AdminDao().insertuser(user);
				//	flag1=new AdminDao().insertbookpermission(user_id);
					
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				if (flag) {
					System.out.println("新增管理员用户成功");
					response.sendRedirect("../admin_user_manage.jsp");
				}else {
					System.out.println("新增管理员用户失败");
					response.sendRedirect("../admin_user_manage.jsp");
				}
			
			}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
