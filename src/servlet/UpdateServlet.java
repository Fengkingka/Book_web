package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	
	 public UpdateServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String book_String =request.getParameter("set");
			String []b_info =book_String.split(",");
			//得到有多少条数据
			int n = b_info.length /11;
			String [][] book_inf= new String[n][11];
			int num=-1;
			for(int i=0;i<n;i++) {
				for(int j=0;j<11;j++) {
					num++;
					book_inf[i][j]=b_info[num];
				}
			}
			boolean flag = false;
				try {
						
					flag = new StudentDao().updateBooks(book_inf);
					
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				if (flag == true) {
					System.out.println("修改图书信息成功");
					out.println("true1");
					//response.sendRedirect("../borrow_state.jsp");
				}else {
					System.out.println("修改图书信息失败");
					out.println("false1");
					//response.sendRedirect("../borrow_state.jsp");
				}
			
			}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
