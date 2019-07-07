package beans;

public class Bookpermission {
	private String user_id;
	private String borrow_booknum;
	private String borrow_limitday;
	private String borrow_reday;
	private String borrow_bookbonum;
	private String borrow_bookhisnum;
	public Bookpermission() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bookpermission(String userid) {
		this.setUser_id(userid);
	}
	public Bookpermission(String userid,String borrowbooknum,String borrowlimitday,String borrowreday,String borrowbookbonum,String borrowbookhisnum) {
		this.setUser_id(userid);
		this.setBorrow_booknum(borrowbookhisnum);
		this.setBorrow_limitday(borrowlimitday);
		this.setBorrow_reday(borrowreday);
		this.setBorrow_bookbonum(borrowbookbonum);
		this.setBorrow_bookhisnum(borrowbookhisnum);
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBorrow_booknum() {
		return borrow_booknum;
	}
	public void setBorrow_booknum(String borrow_booknum) {
		this.borrow_booknum = borrow_booknum;
	}
	public String getBorrow_limitday() {
		return borrow_limitday;
	}
	public void setBorrow_limitday(String borrow_limitday) {
		this.borrow_limitday = borrow_limitday;
	}
	public String getBorrow_reday() {
		return borrow_reday;
	}
	public void setBorrow_reday(String borrow_reday) {
		this.borrow_reday = borrow_reday;
	}
	public String getBorrow_bookbonum() {
		return borrow_bookbonum;
	}
	public void setBorrow_bookbonum(String borrow_bookbonum) {
		this.borrow_bookbonum = borrow_bookbonum;
	}
	public String getBorrow_bookhisnum() {
		return borrow_bookhisnum;
	}
	public void setBorrow_bookhisnum(String borrow_bookhisnum) {
		this.borrow_bookhisnum = borrow_bookhisnum;
	}
}
