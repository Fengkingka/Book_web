package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import net.sf.json.JSONObject;

import utils.DBConnection;
import beans.Book;

public class StudentDao {
	//��¼
	public ResultSet user_login(String username,String password) throws Exception { 
		boolean flag = false;
		Connection conn = null;
		ResultSet rs=null;
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			String sql = "select * from user where user_id='" + username +"'and user_pwd='" + password+"'";
			Statement stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			//stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		//	DBConnection.free(conn,null,null);
		}
		return rs;
	}
	
	
	//����userid�޸�����
	public boolean pwdchang(String user_id,String newpwd) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update user set user_pwd=? where user_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,newpwd);
				ps.setString(2,user_id);
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free1(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}	
	
	
	//����ͼ��
	public boolean borrowBook(String book_id,String borrow_date,String s_r_date,String user_id,String br_state) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "insert into br_book(book_id,borrow_date,s_return_date,user_id,br_state) value(?,?,?,?,?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,book_id);
				ps.setString(2,borrow_date);
				ps.setString(3,s_r_date);
				ps.setString(4,user_id);
				ps.setString(5,br_state);
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
	//�黹ͼ��
	public boolean returnBook(String book_id,String t_r_date,String user_id,String br_state) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update br_book set t_return_date=?,br_state=?,renew_time=? where book_id = ? and user_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,t_r_date);
				ps.setString(2,br_state);
				ps.setString(3,"0");
				ps.setString(4,book_id);
				ps.setString(5,user_id);
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free1(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}	

	//����ͼ��
	public boolean renewBook(String book_id,String s_r_date,String user_id) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update br_book set s_return_date=?,renew_time=?  where book_id = ? and user_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,s_r_date);
				ps.setString(2,"1");
				ps.setString(3,book_id);
				ps.setString(4,user_id);
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free1(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}		

	//��ѯ�û�ĳͼ���Ӧ�黹����
	//����noneΪ���û�δ�����ͼ��
	//����falseΪ�ѹ黹,false1Ϊ��ѯ���ִ���쳣
	public String check_s_r_date(String user_id,String book_id) throws Exception {
		Connection conn = null;
		String b_state="";
		String s_r_date="";
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			String sql = "select * from br_book where book_id='" + book_id +"'and user_id='"+ user_id +"'";
			
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if (rs.next()) {
				String book_state=rs.getString("br_state");
				s_r_date = rs.getString("s_return_date");
				String renew_time =rs.getString("renew_time");
				if(book_state.equals("1")) {
					if (renew_time.equals("1")) {
						return "false2";
					}else {
						return s_r_date;
					}
				}else {
					return "false";
				}
			}else {
				return "none";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "false1";
		} finally {
			DBConnection.free(conn,null,null);
		}
		
	}	
	
	//��ѯ�û�ĳͼ��Ľ軹״̬
	//����noneΪ���û�δ�����ͼ��
	//����trueΪ�ڽ裬falseΪ�ѹ黹
	public String check_bstate(String user_id,String book_id) throws Exception {
		Connection conn = null;
		String b_state="";
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			String sql = "select * from br_book where book_id='" + book_id +"'and user_id='"+user_id+"'";
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if (rs.next()) {
				String book_state=rs.getString("br_state");
				if(book_state.equals("1")) {
					b_state="true";
				}else {
					b_state="false";
				}
			}else {
				b_state="none";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return b_state;//����һ�������б�
	}	
	
	//���û���ͼ�鴦���ѹ黹ʱ���������ͼ��	
	//�޸Ľ軹״̬���������ڡ�Ӧ�黹���ڡ�ʵ�ʹ黹����
	public boolean update_bstate(String book_id,String user_id,String borrow_date,String s_r_date,String t_r_date,String br_state) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update br_book set br_state=?,borrow_date=?,s_return_date=?,t_return_date=? where book_id = ? and user_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,br_state);
				ps.setString(2,borrow_date);
				ps.setString(3,s_r_date);
				ps.setString(4,t_r_date);
				ps.setString(5,book_id);
				ps.setString(6,user_id);
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free1(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}	
	
	//����ѧ���Ƿ����������������true��Ϊ������
	public boolean check_stu_black(String user_id) throws Exception {
		Connection conn = null;
		boolean flag=false;
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			String sql = "select * from user where user_id='" + user_id +"'";
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if (rs.next()) {
				String user_flag=rs.getString("user_flag");
				if(user_flag.equals("0")) {
					flag=true;
				}else {
					flag=false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return flag;//����һ�������б�
	}	
	
	//����ѧ���Ƿ�ﵽ�ɽ���������
	public boolean check_stu_bonum(String user_id) throws Exception {
		Connection conn = null;
		boolean flag=false;
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			String sql = "select * from s_bookpermission where user_id='" + user_id +"'";
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if (rs.next()) {
				String borrow_booknum=rs.getString("borrow_booknum");
				String borrow_bookbonum=rs.getString("borrow_bookbonum");
				if(borrow_booknum.equals(borrow_bookbonum)) {
					//�ﵽ�ɽ���������
					flag=true;
				}else {
					flag=false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return flag;//����һ�������б�
	}	
	
	
	//����ͼ����ڿ������Ƿ�Ϊ0������true����
	public boolean check_bnum(String book_id) throws Exception {
		Connection conn = null;
		boolean flag=false;
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			String sql = "select * from book where book_id='" + book_id +"'";
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if (rs.next()) {
				String book_num=rs.getString("book_num");
				if(book_num.equals("0")) {
					flag=false;
				}else {
					flag=true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return flag;//����һ�������б�
	}		
	
	//�޸�ͼ��Ŀɽ�״̬Ϊ"0"
	public boolean update_bstate(String book_id) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update book set book_state="+'0'+" where book_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,book_id);
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free1(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}	
	
	//�޸�ͼ��Ŀɽ�״̬Ϊ"1"
	public boolean update_bstate1(String book_id) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update book set book_state="+'1'+" where book_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,book_id);
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free1(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}	
	
	//�޸�ͼ��ı����Ĵ���(��һ)
	public boolean update_hisnum(String book_id) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update book set book_hisnum=book_hisnum+"+'1'+" where book_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,book_id);
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free1(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}	

	//�޸�ѧ��ͼ��֤�ĵ�ǰ�ڽ�����(��һ)����ʷ��������(��һ)
	public boolean update_bookper_up(String user_id) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update s_bookpermission set borrow_bookbonum=borrow_bookbonum+"+'1'+",borrow_bookhisnum=borrow_bookhisnum+"+'1'+" where user_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,user_id);
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free1(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}
	
	//�޸�ѧ��ͼ��֤�ĵ�ǰ�ڽ�����(��һ)
	public boolean update_bookper_down(String user_id) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update s_bookpermission set borrow_bookbonum=borrow_bookbonum-"+'1'+" where user_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,user_id);
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free1(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}
	
	
	//�޸�ͼ����ڿ�����(��һ)���黹����(��һ)�ͽ������(��һ)
	public boolean update_bnum_up(String book_id) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update book set book_num=book_num+"+'1'+",book_renum=book_renum+"+'1'+",book_bonum=book_bonum-"+'1'+" where book_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,book_id);
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free1(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}	

	//�޸�ͼ����ڿ�����(��һ)�ͽ������(��һ)
	public boolean update_bnum_down(String book_id) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update book set book_num=book_num-"+'1'+",book_bonum=book_bonum+"+'1'+" where book_id = ?" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,book_id);
				count =ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free1(conn,ps,null);
			}
		
		if(count==1) {
			flag=true;
		}
		return flag;
	}	
	
	//��ѯ�û��ڽ�ͼ����Ϣ
	public ArrayList queryBoBooks(String user_id) throws Exception {
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

	//��ѯ�û��ѹ黹ͼ����Ϣ
	public ArrayList queryReBooks(String user_id) throws Exception {
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
	
	//��ѯ���е�ͼ����Ϣ
	public ArrayList queryAllBooks() throws Exception {
		Connection conn = null;
		ArrayList Books = new ArrayList();
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			String sql = "select * from book";
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
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
				book.setBook_num(rs.getString("book_num"));
				book.setBook_time(rs.getString("book_time"));
				book.setBook_content(rs.getString("book_content"));
				book.setBook_state(rs.getString("book_state"));
				Books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return Books;//����һ�������б�
	}
	//�޸ĵ���ͼ����Ϣ(û�����ݼ��)
	public boolean updateBook(String bookid,String bookISBN,String bookname,String bookauthor,String booktype,String bookpress,String bookprice,String booknum,String booktime,String bookstate) throws Exception { 
		boolean flag = false;
		Connection conn = null;
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			String sql = "update book set book_id='" + bookid + "',book_ISBN='" + bookISBN + "',book_name='" + bookname + "',book_author='" + bookauthor + "',book_type='" + booktype + "',book_press='" + bookpress + "',book_price='" + bookprice + "',book_num='" + booknum + "',book_time='" + booktime + "',book_state='" + bookstate + "' where book_id = " + bookid ;
			Statement stat = conn.createStatement();
			int n = stat.executeUpdate(sql);
			System.out.println("�ɹ�����"+n+"��");
			if (n > 0) {
				flag = true;
			}
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return flag;
	}
	//�޸Ķ��ͼ����Ϣ
	public boolean updateBooks(String [][]book) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
		for(int i=0;i<book.length;i++) {
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "update book set book_name=?,book_ISBN=?,book_author=?,book_type=?,book_press=?,book_price=?,book_num=?,book_bonum=?,book_content=?,book_state=? where book_id = ? ;" ;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,book[i][1]);
				ps.setString(2,book[i][2]);
				ps.setString(3,book[i][3]);
				ps.setString(4,book[i][4]);
				ps.setString(5,book[i][5]);
				ps.setString(6,book[i][6]);
				ps.setString(7,book[i][7]);
				ps.setString(8,book[i][8]);
				ps.setString(9,book[i][9]);
				ps.setString(10, book[i][10]);
				ps.setString(11, book[i][0]);
				count +=ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free(conn,null,null);
			}
		}
		if(count==book.length) {
			flag=true;
		}
		return flag;
	}
	//ɾ��һ�����߶��ͼ����Ϣ
	public boolean deleteBooks(String []book) {
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		//ͳ���޸ĵ�����
		int count=0;
		for(int i=0;i<book.length;i++) {
			// ��ȡ����
			conn = DBConnection.getConnection();
			String sql = "delete from book where book_id=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1,book[i]);
				count +=ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConnection.free(conn,ps,null);
			}
		}
		if(count==book.length) {
			flag=true;
		}
		return flag;
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
			String sql = "insert into book values(?,?,?,?,?,?,?,?,?,?,?,?)";
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
				ps.setString(10,book.getBook_time());
				ps.setString(11,book.getBook_content());
				ps.setString(12, book.getBook_state());
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
	
	//����ͼ�飨��������������ʱ����
	public boolean searchBooks(String bookname) throws Exception { 
		boolean flag = false;
		Connection conn = null;
		
		try {
			// ��ȡ����
			conn = DBConnection.getConnection();
			// ����SQL���
			String sql = "select * from book where book_name="+bookname;
			Statement stat = conn.createStatement();
			int n = stat.executeUpdate(sql);
			System.out.println("�ɹ�����"+n+"��");
			if (n > 0) {
				flag = true;
			}
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(conn,null,null);
		}
		return flag;
	}
	//ͨ�õ����ݿ��ѯ����,paramsԤ�������
    public ResultSet QuerySQL(String sql, Object[] params,Connection conn)  {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //System.out.println(sql+params[0]);
        try {
        	conn = DBConnection.getConnection();
        	pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (params != null) {
                        for (int i = 0; i < params.length; i++) {
                             try {
                                 pstmt.setObject(i + 1, params[i]);
                             } catch (SQLException e) {
                                 e.printStackTrace();
                             }
                         }
                     }
        try {
            rs = pstmt.executeQuery();
            //System.out.println("this1");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
			//DBConnection.free(conn,pstmt,null);
		}
        return rs;
    }
    //�Զ���Ĳ���ͼ�鷽��
    //ʹ��ģ����ѯ
    public ResultSet Querybook(String sql, Object[] params,Connection conn)  {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //System.out.println(sql+params[0]);
        try {
        	conn = DBConnection.getConnection();
        	pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (params != null) {
                        for (int i = 0; i < params.length; i++) {
                             try {
                                 pstmt.setObject(i + 1, "%"+params[i]+"%");
                                 pstmt.setObject(i + 2, "%"+params[i]+"%");
                                 pstmt.setObject(i + 3, "%"+params[i]+"%");
                                 pstmt.setObject(i + 4, "%"+params[i]+"%");
                                 pstmt.setObject(i + 5, "%"+params[i]+"%");
                                 pstmt.setObject(i + 6, "%"+params[i]+"%");
                             } catch (SQLException e) {
                                 e.printStackTrace();
                             }
                         }
                     }
        try {
            rs = pstmt.executeQuery();
            //System.out.println("this1");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
			//DBConnection.free(conn,pstmt,null);
		}
        return rs;
    }  
    
    //������ʹ��ģ����ѯ
    public ResultSet Querybook_name(String sql, Object[] params,Connection conn)  {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //System.out.println(sql+params[0]);
        try {
        	conn = DBConnection.getConnection();
        	pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (params != null) {
                        for (int i = 0; i < params.length; i++) {
                             try {
                                 pstmt.setObject(i + 1, "%"+params[i]+"%");
                             } catch (SQLException e) {
                                 e.printStackTrace();
                             }
                         }
                     }
        try {
            rs = pstmt.executeQuery();
            //System.out.println("this1");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
			//DBConnection.free(conn,pstmt,null);
		}
        return rs;
    }  
    
}
