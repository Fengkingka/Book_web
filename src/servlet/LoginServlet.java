package servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import javafx.scene.control.Alert;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	 public LoginServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			String username =request.getParameter("username");
			String password =request.getParameter("password");
			String message = null;
			ResultSet rs;
			try {
				rs = new StudentDao().user_login(username,password);
				if(rs.next()) {
					request.getSession().setAttribute("user_id", rs.getString(1));
					request.getSession().setAttribute("user_name", rs.getString(2));
					request.getSession().setAttribute("user_type", rs.getString(4));
					request.getSession().setAttribute("user_flag", rs.getString(5));
					//System.out.println(rs.getString(1));
					System.out.println("id:"+rs.getString(1)+","+rs.getString(2)+"µÇÂ¼ÁËÏµÍ³");
					//System.out.println(rs.getString(4));
					//System.out.println(rs.getString(5));
					response.sendRedirect("../index.jsp");
				}else {
					message="false";
					request.getSession().setAttribute("message",message);
					response.sendRedirect("../login.jsp");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

					
			
	}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
