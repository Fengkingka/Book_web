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
		//	System.out.println("��������");
			boolean flag = false;
				try {
					flag = new AdminDao().updateUserspwd(userid);
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				if (flag == true) {
					System.out.println("�˺�:"+userid_string+",����ɹ�");
					out.println("true1");
					//response.sendRedirect("../borrow_state.jsp");
				}else {
					System.out.println("����ʧ��");
					out.println("false1");
					//response.sendRedirect("../borrow_state.jsp");
				}
			
			}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
