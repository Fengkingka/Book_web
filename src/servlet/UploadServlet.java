package servlet;

import java.io.File;
import java.io.IOException;
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

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
             response.sendRedirect("../borrow_manage.jsp");
          
       //      getServletContext().getRequestDispatcher("/tryimg3.jsp").forward(request, response);
         }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
