package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import dao.StudentDao;


@WebServlet("/DeleteuserServlet")
public class DeleteuserServlet extends HttpServlet {
	
	 public DeleteuserServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			String book_String =request.getParameter("set");
			String []userid =book_String.split(",");
			PrintWriter out = response.getWriter();
			boolean flag = false;
				try {
						
					flag = new AdminDao().deleteUsers(userid);
					
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				if (flag == true) {
					System.out.println("删除账号信息成功");
					out.println("true1");
				//	response.sendRedirect("../book_manage.jsp");
				}else {
					System.out.println("删除账号信息失败");
					out.println("false1");
				//	response.sendRedirect("../book_manage.jsp");
				}
			
			}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}

