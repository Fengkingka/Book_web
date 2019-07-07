package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.DBConnection;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	
	 public SearchServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			//接受数据库查询到的JSON数据
			String allString =null;
			String s_book =request.getParameter("set");
			JSONObject all = new JSONObject();
			JSONArray all_book = new JSONArray();
			System.out.println(s_book);
			Connection conn=null;
			conn = DBConnection.getConnection();
			String sql =  "SELECT * FROM book where book_id like ? or book_ISBN like ? or book_name like ? or book_author like ? or book_type like ? or book_press like ? ";
			Object [] params= {s_book};
			ResultSet rs = new StudentDao().Querybook(sql,params,conn);	
			//将数据封装
			try {
				
				while(rs.next()) {
					JSONObject js = new JSONObject();
					js.put("book_id",rs.getObject(1)!=null?rs.getObject(1):"");
					js.put("book_ISBN",rs.getObject(2)!=null?rs.getObject(2):"");
					js.put("book_name",rs.getObject(3)!=null?rs.getObject(3):"");
					js.put("book_author",rs.getObject(4)!=null?rs.getObject(4):"");
					js.put("book_type",rs.getObject(5)!=null?rs.getObject(5):"");
					js.put("book_press",rs.getObject(6)!=null?rs.getObject(6):"");
					js.put("book_price",rs.getObject(7)!=null?rs.getObject(7):"");
					js.put("book_content",rs.getObject(13)!=null?rs.getObject(13):"");
					js.put("book_img", rs.getObject(15)!=null?rs.getObject(15):"");
					all_book.add(js);
					//sender_context.add(js);
				}
				PrintWriter out = response.getWriter();
				DBConnection.free(conn,null,null);
				rs.close();
				all.put("book_info", all_book);
				allString = all.toString();
				out.println(allString);
	             System.out.println(allString);
	             out.flush();
	             out.close();
	             
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e);
		}
		}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
