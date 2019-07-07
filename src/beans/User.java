package beans;

public class User {
	private String userid;
	private String username;
	private String password;
	private String usertype;
	private String user_flag;
	private static User user = new User("asd", "asd");
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userName, String passWord) {
		this.setUserName(userName);
		this.setPassWord(passWord);
	}
	public User(String userid,String username,String password,String usertype,String user_flag) {
		this.userid=userid;
		this.username=username;
		this.password=password;
		this.usertype=usertype;
		this.user_flag=user_flag;
	}
	public User(String userid,String username,String usertype,String user_flag) {
		this.userid=userid;
		this.username=username;
		this.usertype=usertype;
		this.user_flag=user_flag;
	}
	public String getPassWord() {
		return password;
	}

	public void setPassWord(String password) {
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		User.user = user;
	}

	public String check(User u) {
		if (u == null || u != null && u.getUserName() == null && u.getPassWord() == null)
			return "未输入";
		else if (u.getUserName().equals(user.getUserName()) && u.getPassWord().equals(user.getPassWord()))
			return "success";
		else
			return "用户名或密码不对！";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getUser_flag() {
		return user_flag;
	}

	public void setUser_flag(String user_flag) {
		this.user_flag = user_flag;
	}
}
