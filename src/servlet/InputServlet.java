package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import beans.Book;
import dao.AdminDao;
import dao.StudentDao;

@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet{
	 public InputServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String book_id =request.getParameter("book_id");
			String book_ISBN =request.getParameter("book_ISBN");
			String book_name =request.getParameter("book_name");
			String book_author =request.getParameter("book_author");
			String book_press =request.getParameter("book_press");
			String book_price =request.getParameter("book_price");
			String book_runum =request.getParameter("book_runum");
			String book_content =request.getParameter("book_content");
			String book_img =request.getParameter("book_img");
			String book_state= "1";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String book_time =df.format(new Date());
			String book_type =request.getParameter("book_type");
			System.out.println(book_type);
			switch (book_type) {
			case "1":book_type="马列主义毛泽东思想";break;
			case "2":book_type="哲学";break;
			case "3":book_type="社会科学";break;
			case "4":book_type="自然科学";break;
			case "5":book_type="综合";break;
			default:
				break;
			}
			
			Book book =new Book(book_id, book_ISBN, book_name, book_author, book_type, book_press, book_price, book_runum,book_runum,"0","0", book_time, book_content, book_state,book_img);
			boolean flag = false;
				try {
						
					flag = new AdminDao().insertBook(book);
					
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				if (flag == true) {
					System.out.println("新增图书信息成功");
					out.println("true1");
				//	response.sendRedirect("../book_manage.jsp");
				}else {
					System.out.println("新增图书信息失败");
					out.println("false1");
				//	response.sendRedirect("../book_manage.jsp");
				}
				 out.flush();
	             out.close();
/*				
				//导入图片信息
				 FileItemFactory factory = new DiskFileItemFactory();
				 
		         // 创建文件上传处理器
		         ServletFileUpload upload = new ServletFileUpload(factory);
		 
		         // 开始解析请求信息
		         List items = null;
		         try {
		             items = upload.parseRequest(new ServletRequestContext(request));
		         }
		         catch (FileUploadException e) {
		             e.printStackTrace();
		         }
		 
		         // 对所有请求信息进行判断
		         Iterator iter = items.iterator();
		         while (iter.hasNext()) {
		             FileItem item = (FileItem) iter.next();
		             // 信息为普通的格式
		             if (item.isFormField()) {
		                 String fieldName = item.getFieldName();
		                 String value = item.getString();
		                 request.setAttribute(fieldName, value);
		             }
		             // 信息为文件格式
		             else {
		                 String fileName = item.getName();
		                 System.out.println(fileName);
		                 int index = fileName.lastIndexOf("\\");
		                 fileName = fileName.substring(index + 1);
		                 request.setAttribute("realFileName", fileName);
		               // String basePath =request.getSession().getServletContext().getRealPath("/");
		              //  String basePath ="H:\\projects\\data\\";
		              //存储在web项目的images中，方便读取
		                 String basePath ="D:\\try\\Book_web\\WebContent\\images";
		                System.out.println(basePath);
		              //  System.out.println(fileName);
		              //   String basePath = request.getRealPath("/images");
		                 File file = new File(basePath, fileName);
		                 try {
		                     item.write(file);
		                 }
		                 catch (Exception e) {
		                     e.printStackTrace();
		                 }
		             }
		             request.setAttribute("msg","文件上传成功!");
		             getServletContext().getRequestDispatcher("/tryimg3.jsp").forward(request, response);
		         }*/
				
		}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
