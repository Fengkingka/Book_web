package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import dao.AdminDao;
import dao.StudentDao;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@WebServlet("/ImportBookServlet")
public class ImportBookServlet extends HttpServlet {
	
	 public ImportBookServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			SmartUpload su=new SmartUpload();
			//允许上传的文件类型
	        su.setAllowedFilesList("xls");
	        su.setAllowedFilesList("xlsx");
	        
	        su.initialize(getServletConfig(), request, response);
	        String path="H:\\projects\\data\\";
	        Workbook book;
	        boolean flag=false;
	        try {
				su.upload();
				su.save(path);
				String filename=su.getFiles().getFile(0).getFileName();
				book=Workbook.getWorkbook(new File(path+filename));
			//	book=Workbook.getWorkbook(path+filename);
				flag = new AdminDao().import_books(book);
				if(flag) {
					response.sendRedirect("../book_manage.jsp");
					System.out.println("批量图书导入成功");
				}else {
					response.sendRedirect("../book_manage.jsp");
					System.out.println("批量图书导入失败");
				}
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}

