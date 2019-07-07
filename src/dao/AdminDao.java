package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.jspsmart.upload.SmartUpload;

import net.sf.json.JSONObject;

import utils.DBConnection;
import utils.UploadImgs;
import beans.Book;
import beans.User;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
public class AdminDao {
	//��������ѧ���˺�
	public boolean import_stu(Workbook stu) throws Exception { 
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		//ͳ�Ƴɹ�����
		int count=0;
		int count2=0;
		//��õ�һ����������� 
		Sheet sheet=stu.getSheet(0); 
		//�õ���һ�е�һ�еĵ�Ԫ��
		int rowCount=sheet.getRows();
		int inputCount=0;
		
		for(int i=1;i<rowCount;i++)
		{
		 Cell Cid=sheet.getCell(0,i); 
		 String id=Cid.getContents();
		 
		 Cell Cname=sheet.getCell(1,i); 
		 String name=Cname.getContents(); 
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "insert into user(user_id,user_name,user_pwd,user_type) value(?,?,?,?)";
			String sql2 ="insert into s_bookpermission(user_id) values(?)";
			try {
				ps=conn.prepareStatement(sql);
				ps2=conn.prepareStatement(sql2);
				ps.setString(1,id);
				ps.setString(2,name);
				ps.setString(3,"888888");
				ps.setString(4,"1");
				ps2.setString(1,id);
				count +=ps.executeUpdate();
				count2 +=ps2.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free(conn,ps,null);
			}
		}
		stu.close();

		
		if((count==rowCount-1)&&(count2==rowCount-1)) {
			flag=true;
			System.out.println("�ϴ��ļ��ɹ�,����������ݳɹ���");
		}
		return flag;
	}

	//�����ϴ�ͼƬ��Ϣ(����ͼ���ͼƬ��Ϣ)
	public boolean import_books(Workbook book) throws Exception { 
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ�Ƴɹ�����
		int count=0;
		//��õ�һ����������� 
		Sheet sheet=book.getSheet(0); 
		//�õ���һ�е�һ�еĵ�Ԫ��
		int rowCount=sheet.getRows();
		int inputCount=0;
		
		for(int i=1;i<rowCount;i++)
		{
		 Cell Cid=sheet.getCell(0,i); 
		 String book_id=Cid.getContents();
		 
		 Cell CISBN=sheet.getCell(1,i); 
		 String book_ISBN=CISBN.getContents(); 
		 
		 Cell Cname=sheet.getCell(2,i); 
		 String book_name=Cname.getContents();
		 
		 Cell Cauthor=sheet.getCell(3,i); 
		 String book_author=Cauthor.getContents(); 
		 
		 Cell Ctype=sheet.getCell(4,i); 
		 String book_type=Ctype.getContents();
		 
		 Cell Cpress=sheet.getCell(5,i); 
		 String book_press=Cpress.getContents(); 
		 
		 Cell Cprice=sheet.getCell(6,i); 
		 String book_price=Cprice.getContents(); 
		 
		 Cell Crunum=sheet.getCell(7,i); 
		 String book_runum=Crunum.getContents();
		 
		 Cell Ccontent=sheet.getCell(8,i); 
		 String book_content=Ccontent.getContents(); 
		 
		 Cell Cimg=sheet.getCell(9,i); 
		 String book_img=Cimg.getContents();
		 //����ͼƬ�����������images�ļ�����
		 //���ص�ͼƬ�ļ���
		 book_img="images/"+UploadImgs.uploadbookimg(book_img);
		 
		 //���ӵ�ͼ����Ϣ
		 String book_num=book_runum;
		 String book_bonum="0";
		 String book_renum="0";
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 String book_time=df.format(new Date());
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "insert into book(book_id,book_ISBN,book_name,book_author,book_type,book_press,book_price,book_runum,book_num,book_bonum,book_renum,book_time,book_content,book_img) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,book_id);
				ps.setString(2,book_ISBN);
				ps.setString(3,book_name);
				ps.setString(4,book_author);
				ps.setString(5,book_type);
				ps.setString(6,book_press);
				ps.setString(7,book_price);
				ps.setString(8,book_runum);
				ps.setString(9,book_num);
				ps.setString(10,book_bonum);
				ps.setString(11,book_renum);
				ps.setString(12,book_time);
				ps.setString(13,book_content);
				ps.setString(14,book_img);
				count +=ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free(conn,ps,null);
			}
		}
		book.close();

		
		if(count==rowCount-1) {
			flag=true;
			System.out.println("�ϴ��ļ��ɹ�,����������ݳɹ���");
		}
		return flag;
	}
	
	//��ѯ���е�ͼ����Ϣ
	public ArrayList queryBooks() throws Exception {
		Connection conn = null;
		ArrayList Books = new ArrayList();
		PreparedStatement ps = null;
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			//����������
			String sql ="select * from book";
		//	String sql = "select * from br_book where br_state="+'1'+" and user_id='"+user_id+"'";
			Statement stat = conn.createStatement();
		//	ps=conn.prepareStatement(sql);
			ResultSet rs1 = stat.executeQuery(sql);
		//	ps.setString(1,"1");
		//	ps.setString(2, user_id);
		//	ResultSet rs = ps.executeQuery(sql);
			while (rs1.next()) {
				// ʵ����VO
				Book book = new Book();
				book.setBook_id(rs1.getString("book_id"));
				book.setBook_ISBN(rs1.getString("book_ISBN"));
				book.setBook_name(rs1.getString("book_name"));
				book.setBook_author(rs1.getString("book_author"));
				book.setBook_type(rs1.getString("book_type"));
				book.setBook_press(rs1.getString("book_press"));
				book.setBook_price(rs1.getString("book_price"));
				book.setBook_num(rs1.getString("book_num"));
				book.setBook_bonum(rs1.getString("book_bonum"));
				book.setBook_content(rs1.getString("book_content"));
				book.setBook_state(rs1.getString("book_state"));
				Books.add(book);
			}
		//	ResultSet rs2 = stat.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return Books;//����һ�������б�
	}
	
	//��ѯ�û��ڽ�ͼ����Ϣ
	public ArrayList queryBoBooks(String br_state) throws Exception {
		Connection conn = null;
		ArrayList Books = new ArrayList();
		PreparedStatement ps = null;
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			//����������
			String sql ="select a.book_id,b.book_ISBN,b.book_name,b.book_author,b.book_type,b.book_press,b.book_price,b.book_num,b.book_bonum,b.book_hisnum,a.user_id from br_book a,book b where a.book_id = b.book_id and a.br_state='"+br_state+"'";
		//	String sql = "select * from br_book where br_state="+'1'+" and user_id='"+user_id+"'";
			Statement stat = conn.createStatement();
		//	ps=conn.prepareStatement(sql);
			ResultSet rs1 = stat.executeQuery(sql);
		//	ps.setString(1,"1");
		//	ps.setString(2, user_id);
		//	ResultSet rs = ps.executeQuery(sql);
			while (rs1.next()) {
				// ʵ����VO
				Book book = new Book();
				book.setBook_id(rs1.getString("book_id"));
				book.setBook_ISBN(rs1.getString("book_ISBN"));
				book.setBook_name(rs1.getString("book_name"));
				book.setBook_author(rs1.getString("book_author"));
				book.setBook_type(rs1.getString("book_type"));
				book.setBook_press(rs1.getString("book_press"));
				book.setBook_price(rs1.getString("book_price"));
				book.setBook_num(rs1.getString("book_num"));
				book.setBook_bonum(rs1.getString("book_bonum"));
				book.setBook_hisnum(rs1.getString("book_hisnum"));
				book.setBook_userid(rs1.getString("user_id"));
				Books.add(book);
			}
		//	ResultSet rs2 = stat.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return Books;//����һ�������б�
	}
	//��ѯ�û��黹ͼ����Ϣ
	public ArrayList queryReBooks(String br_state) throws Exception {
		Connection conn = null;
		ArrayList Books = new ArrayList();
		PreparedStatement ps = null;
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			//����������
			String sql ="select a.book_id,b.book_ISBN,b.book_name,b.book_author,b.book_type,b.book_press,b.book_price,b.book_num,b.book_renum,a.user_id from br_book a,book b where a.book_id = b.book_id and a.br_state='"+br_state+"'";
		//	String sql = "select * from br_book where br_state="+'1'+" and user_id='"+user_id+"'";
			Statement stat = conn.createStatement();
		//	ps=conn.prepareStatement(sql);
			ResultSet rs1 = stat.executeQuery(sql);
		//	ps.setString(1,"1");
		//	ps.setString(2, user_id);
		//	ResultSet rs = ps.executeQuery(sql);
			while (rs1.next()) {
				// ʵ����VO
				Book book = new Book();
				book.setBook_id(rs1.getString("book_id"));
				book.setBook_ISBN(rs1.getString("book_ISBN"));
				book.setBook_name(rs1.getString("book_name"));
				book.setBook_author(rs1.getString("book_author"));
				book.setBook_type(rs1.getString("book_type"));
				book.setBook_press(rs1.getString("book_press"));
				book.setBook_price(rs1.getString("book_price"));
				book.setBook_num(rs1.getString("book_num"));
				book.setBook_userid(rs1.getString("user_id"));
				Books.add(book);
			}
		//	ResultSet rs2 = stat.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return Books;//����һ�������б�
	}
	//����һ��ͼ����Ϣ
	public boolean insertBook(Book book) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "insert into book(book_id,book_ISBN,book_name,book_author,book_type,book_press,book_price,book_runum,book_num,book_bonum,book_renum,book_time,book_content,book_state,book_img) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,book.getBook_id());
				ps.setString(2,book.getBook_ISBN());
				ps.setString(3,book.getBook_name());
				ps.setString(4,book.getBook_author());
				ps.setString(5,book.getBook_type());
				ps.setString(6,book.getBook_press());
				ps.setString(7,book.getBook_price());
				ps.setString(8,book.getBook_runum());
				ps.setString(9,book.getBook_num());
				ps.setString(10,book.getBook_bonum());
				ps.setString(11,book.getBook_renum());
				ps.setString(12,book.getBook_time());
				ps.setString(13,book.getBook_content());
				ps.setString(14, book.getBook_state());
				ps.setString(15,book.getBook_img());
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}

	//��ѯ���еĹ���Ա��Ϣ
	public ArrayList querymanages() throws Exception {
		Connection conn = null;
		ArrayList Users = new ArrayList();
		PreparedStatement ps = null;
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			String sql ="select * from user where user_type='"+0+"'";
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				// ʵ����VO
				User user = new User();
				user.setUserid(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUsertype(rs.getString("user_type"));
				if(rs.getString("user_flag").equals("1")) {
					user.setUser_flag("��");
				}else {
					user.setUser_flag("��");
				}
				Users.add(user);
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return Users;//����һ�������б�
	}
	//����һ������Ա�û�
	public boolean insert_aduser(User user) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "insert into user(user_id,user_name,user_pwd,user_type) values(?,?,?,?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,user.getUserid());
				ps.setString(2,user.getUserName());
				ps.setString(3,user.getPassWord());
				ps.setString(4,user.getUsertype());
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}
	
	//��ѯ���е�ѧ���˺���Ϣ
	public ArrayList queryusers() throws Exception {
		Connection conn = null;
		ArrayList Users = new ArrayList();
		PreparedStatement ps = null;
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			String sql ="select * from user where user_type='"+1+"'";
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				// ʵ����VO
				User user = new User();
				user.setUserid(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUsertype(rs.getString("user_type"));
				if(rs.getString("user_flag").equals("1")) {
					user.setUser_flag("��");
				}else {
					user.setUser_flag("��");
				}
				Users.add(user);
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return Users;//����һ�������б�
	}
	
	//����һ��ѧ���û�
	public boolean insertuser(User user) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "insert into user(user_id,user_name,user_pwd,user_type) values(?,?,?,?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,user.getUserid());
				ps.setString(2,user.getUserName());
				ps.setString(3,user.getPassWord());
				ps.setString(4,user.getUsertype());
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}
	
	//����һ��ѧ���û��Ķ���֤
	public boolean insertbookpermission(String user_id) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "insert into s_bookpermission(user_id) values(?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,user_id);
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}
	
	
	//��ʼ����������ѧ���û�������
	public boolean updateUserspwd(String []userid) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
		for(int i=0;i<userid.length;i++) {
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update user set user_pwd=? where user_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,"888888");
				ps.setString(2,userid[i]);
				count +=ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free(conn,null,null);
			}
		}
		if(count==userid.length) {
			flag=true;
		}
		return flag;
	}
	//���뵥�������û��������
	public boolean updateUserflag0(String []userid) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
		for(int i=0;i<userid.length;i++) {
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update user set user_flag=? where user_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,"0");
				ps.setString(2,userid[i]);
				count +=ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free(conn,null,null);
			}
		}
		if(count==userid.length) {
			flag=true;
		}
		return flag;
	}
	//���������û��Ƴ�������
	public boolean updateUserflag1(String []userid) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
		for(int i=0;i<userid.length;i++) {
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update user set user_flag=? where user_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,"1");
				ps.setString(2,userid[i]);
				count +=ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free(conn,null,null);
			}
		}
		if(count==userid.length) {
			flag=true;
		}
		return flag;
	}
	
	//ɾ��һ�����߶���û��˺���Ϣ
	public boolean deleteUsers(String []userid) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
		for(int i=0;i<userid.length;i++) {
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "delete from user where user_id=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,userid[i]);
				count +=ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free(conn,ps,null);
			}
		}
		if(count==userid.length) {
			flag=true;
		}
		return flag;
	}
	
	//��ѯ��������Ա�ڽ�ͼ����Ϣ
	public ArrayList adminqueryBoBooks(String user_id) throws Exception {
		Connection conn = null;
		ArrayList Books = new ArrayList();
		PreparedStatement ps = null;
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			//����������
			String sql ="select a.book_id,b.book_ISBN,b.book_name,b.book_author,b.book_type,b.book_press,b.book_price,a.s_return_date from br_book a,book b where a.book_id = b.book_id and a.br_state="+'1'+" and a.user_id='"+user_id+"'";
		//	String sql = "select * from br_book where br_state="+'1'+" and user_id='"+user_id+"'";
			Statement stat = conn.createStatement();
		//	ps=conn.prepareStatement(sql);
			ResultSet rs1 = stat.executeQuery(sql);
		//	ps.setString(1,"1");
		//	ps.setString(2, user_id);
		//	ResultSet rs = ps.executeQuery(sql);
			while (rs1.next()) {
				// ʵ����VO
				Book book = new Book();
				book.setBook_id(rs1.getString("book_id"));
				book.setBook_ISBN(rs1.getString("book_ISBN"));
				book.setBook_name(rs1.getString("book_name"));
				book.setBook_author(rs1.getString("book_author"));
				book.setBook_type(rs1.getString("book_type"));
				book.setBook_press(rs1.getString("book_press"));
				book.setBook_price(rs1.getString("book_price"));
				book.setBook_s_return_date(rs1.getString("s_return_date"));
				Books.add(book);
			}
		//	ResultSet rs2 = stat.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return Books;//����һ�������б�
	}	
	
	
	//��ѯ��������Ա�ѹ黹ͼ����Ϣ
		public ArrayList adminqueryReBooks(String user_id) throws Exception {
			Connection conn = null;
			ArrayList Books = new ArrayList();
			PreparedStatement ps = null;
			try {
				// ��ȡ����
				conn = DBConnection.getConnection();
				// ����SQL���
				String sql = "select a.book_id,b.book_ISBN,b.book_name,b.book_author,b.book_type,b.book_press,b.book_price,a.t_return_date from br_book a,book b where a.book_id = b.book_id and a.br_state="+'0'+" and a.user_id='"+user_id+"'";
				Statement stat = conn.createStatement();
			//	ps=conn.prepareStatement(sql);
				ResultSet rs = stat.executeQuery(sql);
			//	ps.setString(1,"0");
			//	ps.setString(2, user_id);
			//	ResultSet rs = ps.executeQuery(sql);
				while (rs.next()) {
					// ʵ����VO
					Book book = new Book();
					book.setBook_id(rs.getString("book_id"));
					book.setBook_ISBN(rs.getString("book_ISBN"));
					book.setBook_name(rs.getString("book_name"));
					book.setBook_author(rs.getString("book_author"));
					book.setBook_type(rs.getString("book_type"));
					book.setBook_press(rs.getString("book_press"));
					book.setBook_price(rs.getString("book_price"));
					book.setBook_t_return_date(rs.getString("t_return_date"));
					Books.add(book);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.free(conn,null,null);
			}
			return Books;//����һ�������б�
		}	
	
}
