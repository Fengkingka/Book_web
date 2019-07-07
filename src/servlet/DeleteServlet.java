package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;


@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	
	 public DeleteServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			String book_String =request.getParameter("set");
			String []b_info =book_String.split(",");
			boolean flag = false;
				try {
						
					flag = new StudentDao().deleteBooks(b_info);
					
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				if (flag == true) {
					System.out.println("ɾ��ͼ����Ϣ�ɹ�");
					response.sendRedirect("../book_manage.jsp");
				}else {
					System.out.println("ɾ��ͼ����Ϣʧ��");
					response.sendRedirect("../book_manage.jsp");
				}
			
			}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}

