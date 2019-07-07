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


@WebServlet("/BlackServlet")
public class BlackServlet extends HttpServlet {
	
	 public BlackServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String userid_string =request.getParameter("set");
			String userflag =request.getParameter("userflag");
			String []userid =userid_string.split(",");
			//���������
			if(userflag.equals("in")) {
				boolean flag = false;
				try {
					flag = new AdminDao().updateUserflag0(userid);
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				if (flag == true) {
					System.out.println("�˺�:"+userid_string+",����������ɹ�");
					out.println("true1");
					//response.sendRedirect("../borrow_state.jsp");
				}else {
					System.out.println("���������ʧ��");
					out.println("false1");
					//response.sendRedirect("../borrow_state.jsp");
				}
			}
			//�Ƴ�������
			else if(userflag.equals("out")){
				boolean flag = false;
				try {
					flag = new AdminDao().updateUserflag1(userid);
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				if (flag == true) {
					System.out.println("�˺�:"+userid_string+",�Ƴ��������ɹ�");
					out.println("true2");
					//response.sendRedirect("../borrow_state.jsp");
				}else {
					System.out.println("�Ƴ�������ʧ��");
					out.println("false2");
					//response.sendRedirect("../borrow_state.jsp");
				}
			}

			
			}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
