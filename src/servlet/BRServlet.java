package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import dao.StudentDao;

@WebServlet("/BRServlet")
public class BRServlet extends HttpServlet{
	 public BRServlet() {
	        super();
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			//��ȡͼ����
			String book_id =request.getParameter("set");
			//��ȡִ���ĸ����������ġ��黹������
			String brxbook =request.getParameter("how");
			System.out.print(brxbook);
			//��ȡ�û�������
			String user_type=request.getParameter("user_type");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
			Calendar ca = Calendar.getInstance();
	        ca.add(Calendar.DATE, 30);
	        //��ǰ����
			String b_date=df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
			//Ӧ�黹����
			String s_r_date =df.format(ca.getTime());
			//����ʱ��Ӧ�黹����
			String s_r_date1 =null;
			//������id
			String user_id=request.getParameter("user_id");;
			//�軹״̬,1Ϊ�ڽ裬0Ϊ�ѻ�
			String br_state1= "1";
			String br_state0= "0";
			//���ġ��黹�������Ƿ�ɹ�
			boolean flag1 = false;
			boolean flag2 = false;
			boolean flag3 = false;
			//ѧ���Ƿ�Ϊ������
			boolean flag4 =false;
			PrintWriter out = response.getWriter();
				try {
					//�Ȳ�ѯѧ���Ƿ����������,trueΪ������
					flag4=new StudentDao().check_stu_black(user_id);
					if (flag4) {
						System.out.println("���ɽ���,�������������");
						out.println("false41");
					}else {
						if (brxbook.equals("bobook")) {
							//�����ѧ��
							if(!user_type.equals("2")) {
								//��ѯ��ѧ���Ƿ��ɿɽ���������
								if(new StudentDao().check_stu_bonum(user_id)) {
									System.out.println("���ɽ���,�Ѵ�ɽ�����");
									out.println("false51");
								}else {
									//�Ȳ�ѯ��ͼ���Ƿ�ɽ�(���ڿ������Ƿ�Ϊ0)
									//Ȼ���ѯ���û��Ƿ��н軹����ͼ��
									//�ټ���ͼ��Ľ軹״̬
									boolean flag11=false;
									flag11=new StudentDao().check_bnum(book_id);
									if (!flag11) {
										System.out.println("��ͼ�鲻�ɽ裬�ڿ�����Ϊ0����ȫ�����");
										//��ͼ��״̬��Ϊ���ɽ�
										if(new StudentDao().update_bstate(book_id)) {
											out.println("false13");
										}
									}else {
										String flag="";
										flag =new StudentDao().check_bstate(user_id, book_id);
										if(flag.equals("true")) {
											System.out.println("��ͼ���ڽ���");
											out.println("false1");
										}else if (flag.equals("false")) {
											flag1 = new StudentDao().update_bstate(book_id, user_id, b_date, s_r_date,"",br_state1);
											boolean flag12=false;
											flag12 = new StudentDao().update_bnum_down(book_id);
											if (flag1&&flag12) {
												//ͼ�鱻���Ĵ�����һ
												//ѧ������֤��ʷ������������ǰ�ڽ�������һ
												if(user_type.equals("2")) {
													if(new StudentDao().update_hisnum(book_id)) {
														System.out.println("����ͼ��ɹ�");
														out.println("true1");
													}
												}
												else if (new StudentDao().update_hisnum(book_id)&&new StudentDao().update_bookper_up(user_id)) {
													System.out.println("����ͼ��ɹ�");
													out.println("true1");
												}
											}else {
												System.out.println("����ͼ��ʧ�ܣ�����sql���1������߸ı�����sql������");
												out.println("false11");
											}
										}else {
											flag1 = new StudentDao().borrowBook(book_id,b_date,s_r_date,user_id,br_state1);
											boolean flag13=false;
											flag13 =new StudentDao().update_bnum_down(book_id);
											if (flag1) {
												//ͼƬ�����Ĵ�����һ
												//ѧ������֤��ʷ������������ǰ�ڽ�������һ
												if(user_type.equals("2")) {
													if(new StudentDao().update_hisnum(book_id)) {
														System.out.println("����ͼ��ɹ�");
														out.println("true1");
													}
												}
												else if (new StudentDao().update_hisnum(book_id)&&new StudentDao().update_bookper_up(user_id)) {
													System.out.println("����ͼ��ɹ�");
													out.println("true1");
												}
											}else {
												System.out.println("����ͼ��ʧ�ܣ�����sql���2����");
												out.println("false12");
											}
										}	
									}
								}
							}
							//����ǳ�������Ա
							else {
								//�Ȳ�ѯ��ͼ���Ƿ�ɽ�(���ڿ������Ƿ�Ϊ0)
								//Ȼ���ѯ���û��Ƿ��н軹����ͼ��
								//�ټ���ͼ��Ľ軹״̬
								boolean flag11=false;
								flag11=new StudentDao().check_bnum(book_id);
								if (!flag11) {
									System.out.println("��ͼ�鲻�ɽ裬�ڿ�����Ϊ0����ȫ�����");
									//��ͼ��״̬��Ϊ���ɽ�
									if(new StudentDao().update_bstate(book_id)) {
										out.println("false13");
									}
								}else {
									String flag="";
									flag =new StudentDao().check_bstate(user_id, book_id);
									if(flag.equals("true")) {
										System.out.println("��ͼ���ڽ���");
										out.println("false1");
									}else if (flag.equals("false")) {
										flag1 = new StudentDao().update_bstate(book_id, user_id, b_date, s_r_date,"",br_state1);
										boolean flag12=false;
										flag12 = new StudentDao().update_bnum_down(book_id);
										if (flag1&&flag12) {
											//ͼ�鱻���Ĵ�����һ
											//ѧ������֤��ʷ������������ǰ�ڽ�������һ
											if(user_type.equals("2")) {
												if(new StudentDao().update_hisnum(book_id)) {
													System.out.println("����ͼ��ɹ�");
													out.println("true1");
												}
											}
											else if (new StudentDao().update_hisnum(book_id)&&new StudentDao().update_bookper_up(user_id)) {
												System.out.println("����ͼ��ɹ�");
												out.println("true1");
											}
										}else {
											System.out.println("����ͼ��ʧ�ܣ�����sql���1������߸ı�����sql������");
											out.println("false11");
										}
									}else {
										flag1 = new StudentDao().borrowBook(book_id,b_date,s_r_date,user_id,br_state1);
										boolean flag13=false;
										flag13 =new StudentDao().update_bnum_down(book_id);
										if (flag1) {
											//ͼƬ�����Ĵ�����һ
											//ѧ������֤��ʷ������������ǰ�ڽ�������һ
											if(user_type.equals("2")) {
												if(new StudentDao().update_hisnum(book_id)) {
													System.out.println("����ͼ��ɹ�");
													out.println("true1");
												}
											}
											else if (new StudentDao().update_hisnum(book_id)&&new StudentDao().update_bookper_up(user_id)) {
												System.out.println("����ͼ��ɹ�");
												out.println("true1");
											}
										}else {
											System.out.println("����ͼ��ʧ�ܣ�����sql���2����");
											out.println("false12");
										}
									}	
								}
							}

						}else if(brxbook.equals("rebook")) {
							//�Ȳ�ѯ���û��Ƿ��н軹����ͼ��
							//Ȼ���ѯ�Ƿ��ͼ�鴦���ڽ�״̬
							//trueΪ�ڽ裬falseΪ�ѹ黹
							String flag="";
							flag =new StudentDao().check_bstate(user_id, book_id);
							if (flag.equals("true")) {
								flag2 = new StudentDao().returnBook(book_id,b_date,user_id, br_state0);
								boolean flag21=false;
								flag21 =new StudentDao().update_bnum_up(book_id);
								if(flag2&&flag21){
									//��ͼƬ��Ϊ�ɽ�״̬1
									//��ѧ������֤�ĵ�ǰ�ڽ�������һ
									if(new StudentDao().update_bstate1(book_id)&&new StudentDao().update_bookper_down(user_id)) {
										System.out.println("�黹ͼ��ɹ�");
										out.println("true2");
									}
								}else {
									System.out.println("�黹ͼ��ʧ��");
									out.println("false2");
								}
							}else if (flag.equals("false")) {
								System.out.println("��ͼ���ѹ黹");
								out.println("false21");							
							}else {
								System.out.println("��û�н��Ĺ���ͼ��");
								out.println("false22");							
							}
						}else {
							//��ѯ���û��Ƿ���Ĺ���ͼ��
							//�ټ���ͼ��Ľ軹״̬
							String flag="";
							flag = new StudentDao().check_s_r_date(user_id, book_id);
							if(flag.equals("false")) {
								System.out.println("��ͼ���ѹ黹");
								out.println("false31");							
							}else if (flag.equals("false1")) {
								System.out.println("����ͼ��ʧ�ܣ���ѯsql������");
								out.println("false32");
							}else if (flag.equals("none")) {
								System.out.println("��û�н��Ĺ���ͼ��");
								out.println("false33");	
							}else if (flag.equals("false2")) {
								System.out.println("���Ѵ��������������������ͼ��");
								out.println("false35");
							}
							else {
								Date date1 = df.parse(flag);
								ca.setTime(date1);//���õ�Calendar��
								ca.add(Calendar.DATE, 10);
								flag=df.format(ca.getTime());
								flag3 = new StudentDao().renewBook(book_id, flag, user_id);
								if(flag3){
									System.out.println("����ͼ��ɹ�");
									out.println("true3");
								}else {
									System.out.println("����ͼ��ʧ�ܣ�����sql������");
									out.println("false34");
								}
							}	
						}
					}
			
					
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
	             out.flush();
	             out.close();
			}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
}
