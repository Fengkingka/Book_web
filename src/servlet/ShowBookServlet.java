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

@WebServlet("/ShowBookServlet")
public class ShowBookServlet extends HttpServlet {
	
	 public ShowBookServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			//接受数据库查询到的JSON数据
			String allString =null;
		//	String s_book =request.getParameter("set");
			String user_id =request.getParameter("user_id");
		//	System.out.println(user_id);
			JSONObject all = new JSONObject();
			JSONArray all_book = new JSONArray();
			JSONArray all_hotbook = new JSONArray();
			JSONArray all_bookper = new JSONArray();
			Connection conn=null;
			conn = DBConnection.getConnection();
			String sql =  "select * from book order by book_id desc limit 0,4";
			String sql2 ="select * from book order by book_hisnum desc limit 0,4";
			String sql3 ="select * from s_bookpermission where user_id='" + user_id +"'";
			Object [] params= {};
			ResultSet rs = new StudentDao().QuerySQL(sql,params,conn);
			ResultSet rs1 = new StudentDao().QuerySQL(sql2,params,conn);
			ResultSet rs2 =new StudentDao().QuerySQL(sql3,params,conn);
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
				while(rs1.next()) {
					JSONObject js = new JSONObject();
					js.put("book_id",rs1.getObject(1)!=null?rs1.getObject(1):"");
					js.put("book_ISBN",rs1.getObject(2)!=null?rs1.getObject(2):"");
					js.put("book_name",rs1.getObject(3)!=null?rs1.getObject(3):"");
					js.put("book_author",rs1.getObject(4)!=null?rs1.getObject(4):"");
					js.put("book_type",rs1.getObject(5)!=null?rs1.getObject(5):"");
					js.put("book_press",rs1.getObject(6)!=null?rs1.getObject(6):"");
					js.put("book_price",rs1.getObject(7)!=null?rs1.getObject(7):"");
					js.put("book_content",rs1.getObject(13)!=null?rs1.getObject(13):"");
					js.put("book_img", rs1.getObject(15)!=null?rs1.getObject(15):"");
					all_hotbook.add(js);
					//sender_context.add(js);
				}
				while(rs2.next()) {
					JSONObject js = new JSONObject();
					js.put("user_id",rs2.getObject(1)!=null?rs2.getObject(1):"");
					js.put("borrow_booknum",rs2.getObject(2)!=null?rs2.getObject(2):"");
					js.put("borrow_limitday",rs2.getObject(3)!=null?rs2.getObject(3):"");
					js.put("borrow_reday",rs2.getObject(4)!=null?rs2.getObject(4):"");
					js.put("borrow_bookbonum",rs2.getObject(5)!=null?rs2.getObject(5):"");
					js.put("borrow_bookhisnum",rs2.getObject(6)!=null?rs2.getObject(6):"");
					all_bookper.add(js);
				}
				PrintWriter out = response.getWriter();
				DBConnection.free(conn,null,null);
				rs.close();
				rs1.close();
				rs2.close();
				all.put("book_info", all_book);
				all.put("book_hotinfo", all_hotbook);
				all.put("book_perinfo", all_bookper);
				allString = all.toString();
				out.println(allString);
			//		System.out.print(all_bookper.toString());
	        //     System.out.println(allString);
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
