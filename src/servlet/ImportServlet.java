package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import utils.DBConnection;

@WebServlet("/ImportServlet")
public class ImportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ImportServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		SmartUpload su=new SmartUpload();
		Connection con=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		
		String sql1=null;
		String sql2=null;
		
		//允许上传的文件类型
        su.setAllowedFilesList("xls");
        su.setAllowedFilesList("xlsx");
        
		su.initialize(getServletConfig(), request, response);;	// 初始化上传操作
		try {
		su.upload();
/*		String path=getServletContext().getRealPath("/")+"upload/";*/
		String path="C:\\Users\\Torrore\\Desktop\\";
		System.out.print(path);
		su.save(path);
		String filename=su.getFiles().getFile(0).getFileName();
		Workbook book=Workbook.getWorkbook(new File(path+filename)); 
		//获得第一个工作表对象 
		Sheet sheet=book.getSheet(0); 
		//得到第一列第一行的单元格
		int rowCount=sheet.getRows();
/*		int colCount=sheet.getColumns();*/
		int inputCount=0;
		for(int i=1;i<rowCount;i++)
		{
		 Cell Cid=sheet.getCell(0,i); 
		 String id=Cid.getContents();
		 
		 Cell Cname=sheet.getCell(1,i); 
		 String name=Cname.getContents(); 
		 
		 Cell Csex=sheet.getCell(2,i); 
		 String sex=Csex.getContents();
		 
		 Cell Cclasss=sheet.getCell(3,i); 
		 String classs=Cclasss.getContents();
		 
		 Cell Crole=sheet.getCell(4,i); 
		 String role=Crole.getContents();

		 Cell Cposition=sheet.getCell(5,i); 
		 String position=Cposition.getContents();
		 
		 Cell Cphone=sheet.getCell(6,i); 
		 String phone=Cphone.getContents();
		 
		 Cell Cemail=sheet.getCell(7,i); 
		 String email=Cemail.getContents();
		 
		 Cell Ccomments=sheet.getCell(8,i); 
		 String comments=Ccomments.getContents();
			
		 
		 //写一个检验每一条信息是否正确的方法。失败就break跳到下一个i，即下一行数据。
		 
		 sql1="Insert into user values(?,?,?,?,?,?,?,?,?);";//9条用户信息,3条密保信息
		 sql2="Insert into password values(?,?,?,'666666','您的初始密码是多少？','666666');";
		 con=DBConnection.getConnection();
		 ps1=con.prepareStatement(sql1);
		 ps2=con.prepareStatement(sql2);
		 
		 ps1.setString(1, id);
		 ps1.setString(2, name);
		 ps1.setString(3, sex);
		 ps1.setString(4, classs);
		 ps1.setString(5, role);
		 ps1.setString(6, position);
		 ps1.setString(7, phone);
		 ps1.setString(8, email);
		 ps1.setString(9, comments);
		 
		 ps2.setString(1, id);
		 ps2.setString(2, name);
		 ps2.setString(3, role);
		 
		 System.out.println(sql1);
		 System.out.println(sql2);
		 
		 int count=ps1.executeUpdate();
		 ps2.executeUpdate();
		 inputCount=inputCount+count;
		 }

		book.close(); 
		HttpSession session=request.getSession();
		System.out.println("上传文件"+filename+"成功,导入"+inputCount+"条数据");
		session.setAttribute("message","<script>alert(\"上传文件"+filename+"成功,导入"+inputCount+"条数据\");</script>");
		response.sendRedirect("../borrow_state.jsp");
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		} catch (JXLException e) {
			e.printStackTrace();
		}
	}
}
