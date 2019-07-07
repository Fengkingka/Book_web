package beans;

import java.util.ArrayList;

public class Book {
	private String book_id;
	private String book_ISBN;
	private String book_name;
	private String book_author;
	private String book_type;
	private String book_press;
	private String book_price;
	private String book_runum;
	private String book_num;
	private String book_bonum;
	private String book_renum;
	private String book_time;
	private String book_content;
	private String book_state;
	private String book_img;
	private String book_hisnum;
	private String book_userid;
	private String book_s_return_date;
	private String book_t_return_date;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String bookid,String bookISBN,String bookname,String bookauthor,String booktype,String bookpress,String bookprice,String bookrunum,String booknum,String book_bonum,String book_renum,String booktime,String bookcontent,String bookstate,String bookimg) {
		super();
		this.setBook_id(bookid);
		this.setBook_ISBN(bookISBN);
		this.setBook_name(bookname);
		this.setBook_author(bookauthor);
		this.setBook_type(booktype);
		this.setBook_press(bookpress);
		this.setBook_price(bookprice);
		this.setBook_runum(bookrunum);
		this.setBook_num(booknum);
		this.setBook_bonum(book_bonum);
		this.setBook_renum(book_renum);
		this.setBook_time(booktime);
		this.setBook_content(bookcontent);
		this.setBook_state(bookstate);
		this.setBook_img(bookimg);
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getBook_ISBN() {
		return book_ISBN;
	}
	public void setBook_ISBN(String book_ISBN) {
		this.book_ISBN = book_ISBN;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public String getBook_type() {
		return book_type;
	}
	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}
	public String getBook_press() {
		return book_press;
	}
	public void setBook_press(String book_press) {
		this.book_press = book_press;
	}
	public String getBook_price() {
		return book_price;
	}
	public void setBook_price(String book_price) {
		this.book_price = book_price;
	}
	public String getBook_num() {
		return book_num;
	}
	public void setBook_num(String book_num) {
		this.book_num = book_num;
	}
	public String getBook_time() {
		return book_time;
	}
	public void setBook_time(String book_time) {
		this.book_time = book_time;
	}
	public String getBook_content() {
		return book_content;
	}
	public void setBook_content(String book_content) {
		this.book_content = book_content;
	}
	public String getBook_state() {
		return book_state;
	}
	public void setBook_state(String book_state) {
		this.book_state = book_state;
	}
	public String getBook_runum() {
		return book_runum;
	}
	public void setBook_runum(String book_runum) {
		this.book_runum = book_runum;
	}
	public String getBook_bonum() {
		return book_bonum;
	}
	public void setBook_bonum(String book_bonum) {
		this.book_bonum = book_bonum;
	}
	public String getBook_renum() {
		return book_renum;
	}
	public void setBook_renum(String book_renum) {
		this.book_renum = book_renum;
	}
	public String getBook_img() {
		return book_img;
	}
	public void setBook_img(String book_img) {
		this.book_img = book_img;
	}
	public String getBook_hisnum() {
		return book_hisnum;
	}
	public void setBook_hisnum(String book_hisnum) {
		this.book_hisnum = book_hisnum;
	}
	public String getBook_userid() {
		return book_userid;
	}
	public void setBook_userid(String book_userid) {
		this.book_userid = book_userid;
	}
	public String getBook_s_return_date() {
		return book_s_return_date;
	}
	public void setBook_s_return_date(String book_s_return_date) {
		this.book_s_return_date = book_s_return_date;
	}
	public String getBook_t_return_date() {
		return book_t_return_date;
	}
	public void setBook_t_return_date(String book_t_return_date) {
		this.book_t_return_date = book_t_return_date;
	}
}
