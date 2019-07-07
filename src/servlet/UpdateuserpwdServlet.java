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


@WebServlet("/UpdateuserpwdServlet")
public class UpdateuserpwdServlet extends HttpServlet {
	
	 public UpdateuserpwdServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String userid_string =request.getParameter("set");
			String []userid =userid_string.split(",");
		//	System.out.println("进入重设");
			boolean flag = false;
				try {
					flag = new AdminDao().updateUserspwd(userid);
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				if (flag == true) {
					System.out.println("账号:"+userid_string+",重设成功");
					out.println("true1");
					//response.sendRedirect("../borrow_state.jsp");
				}else {
					System.out.println("重设失败");
					out.println("false1");
					//response.sendRedirect("../borrow_state.jsp");
				}
			
			}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
