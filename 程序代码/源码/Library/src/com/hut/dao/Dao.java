package com.hut.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hut.model.Back;
import com.hut.model.BookInfo;
import com.hut.model.BookType;
import com.hut.model.Borrow;
import com.hut.model.Operater;
import com.hut.model.Order;
import com.hut.model.OrderAndBookInfo;
import com.hut.model.Reader;
import com.hut.model.user;

public class Dao {
	protected static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	protected static String dbUrl = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=library";
	protected static String dbUser = "sa";
	protected static String dbpwd = "hezhiyu";
	protected static String second = null;
	private static Connection conn = null;

	// 连接数据库
	private Dao() {
		try {
			if (conn == null) {

				System.out.println("***********Dao1**********");
				Class.forName(dbClassName);
				conn = DriverManager.getConnection(dbUrl, dbUser, dbpwd);
				System.out.println("数据库连接成功");
				System.out.println("************Dao 2***********");
			} else
				return;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	 private static ResultSet executeQuery(String sql){
		 try {
			if(conn==null){
				 new Dao();
				 System.out.println("**********ResultSet executeQuery***sql=  "+sql);
				 return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			 }
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			// return null;
		}
		return null;
		}

	 private static int executeUpdate(String sql){
		 try {
				if(conn==null)
					new Dao();
				System.out.println("***********executeUpdate**********  sql="+sql);
				return conn.createStatement().executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return -1;
			} 
	 }
	 
	 //关闭数据库
	 public static void close(){
		 try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			conn=null;
		}
		 
	 }
	 
	 
	// 管理员登陆方法
	public static Operater check(String name,String password){
		int i=0;
		Operater operater=new Operater();
		String sql="select *  from tb_operator where name='" + name+ "' and password='" + password + "'and admin=1";
		ResultSet rs=Dao.executeQuery(sql);
		
		try {
			while(rs.next()){
				String names=rs.getString(1);
				operater.setId(rs.getString("id"));
				operater.setName(rs.getString("name"));
				operater.setGrade(rs.getString("admin"));
				operater.setPassword(rs.getString("paaword"));
				
				if(name!=null){
					i=1;
				}
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Dao.close();
		return operater;
	}
	
	/*
	 * 查询类别方法
	 */
	
	public static List selectBookCategory(){
		List list=new ArrayList();
		String sql="select * from tb_bookType";
		ResultSet rs=Dao.executeQuery(sql);
		
		try {
			while(rs.next()){
				BookType  bookType=new BookType();
				bookType.setId(rs.getString("id"));
				bookType.setTypeName(rs.getString("typeName"));
				bookType.setDays(rs.getString("days"));
				bookType.setFk(rs.getString("fk"));
				list.add(bookType);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Dao.close();
		return list;
		
	}
	
	public static List selectBookCategory(String bookType){
		List list=new ArrayList();
		String sql="select * from tb_bookType where typeName='"+bookType+"'";
		ResultSet rs=Dao.executeQuery(sql);
		
		try {
			while(rs.next()){
				BookType type=new BookType();
				type.setDays(rs.getString("days"));
				list.add(type);
				
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	/*
	 * 图书类别表相关操作
	 */
	
	//添加图书类别
	public static int InsertBookType(String bookTypeName,String days,Double fk){
		int i=0;
		try{
			String sql="insert into tb_bookType(typeName,days,fk) values('"+bookTypeName+"','"+days+"',"+fk+")";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	//修改图书类别
	public static int UpdatebookType(String id,String typeName,String days,String fk){
		int i=0;
		try{
			String sql="update tb_bookType set typeName='"+typeName+"',days='"+days+"',fk='"+fk+"' where id='"+id+"'";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
/*	//删除图书类别
	public static int DelbookType(String id){
		int i=0;
		try{
			String sql="delete from tb_bookType where id='"+id+"'";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
*/
	
	//取每种书超过规定时间罚款金额
	public static List selectBookTypeFk(String bookType) {
		List list=new ArrayList();
		String sql = "select *  from tb_bookType where typeName='"+bookType+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType type=new BookType();
				type.setFk(rs.getString("fk"));
				type.setDays(rs.getString("days"));
				list.add(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
		
	}
	
	/*
	 * 图书信息表的相关操作
	 */
	
	//插入图书信息
	public static int Insertbook(String ISBN,String typeId,String bookname,String writer,String translator,String publisher,Date date,Double price){
		int i=0;
		try{
			String sql="insert into tb_bookInfo(ISBN,typeId,bookname,writer,translator,publisher,date,price) values('"+ISBN+"','"+typeId+"','"+bookname+"','"+writer+"','"+translator+"','"+publisher+"','"+date+"',"+price+")";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Dao.close();
		return i;
	}
	
	//查询图书信息
	public static List selectBookInfo() {
		List list=new ArrayList();
		String sql = "select *  from tb_bookInfo";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(rs.getString("ISBN"));
				bookinfo.setTypeid(rs.getString("typeid"));
				bookinfo.setBookname(rs.getString("bookname"));
				bookinfo.setWriter(rs.getString("writer"));
				bookinfo.setTranslator(rs.getString("translator"));
				bookinfo.setPublisher(rs.getString("publisher"));
				bookinfo.setDate(rs.getDate("date"));
				bookinfo.setPrice(rs.getDouble("price"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//查询图书信息  传参 ISBM
	public static List selectBookInfo(String ISBN) {
		List list=new ArrayList();
		String sql = "select *  from tb_bookInfo where ISBN='"+ISBN+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(rs.getString("ISBN"));
				bookinfo.setTypeid(rs.getString("typeid"));
				bookinfo.setBookname(rs.getString("bookname"));
				bookinfo.setWriter(rs.getString("writer"));
				bookinfo.setTranslator(rs.getString("translator"));
				bookinfo.setPublisher(rs.getString("publisher"));
				bookinfo.setDate(rs.getDate("date"));
				bookinfo.setPrice(rs.getDouble("price"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//修改图书信息方法
	public static int Updatebook(String ISBN,String typeId,String bookname,String writer,String translator,String publisher,Date date,Double price){
		int i=0;
		try{
			String sql="update tb_bookInfo set ISBN='"+ISBN+"',typeId='"+typeId+"',bookname='"+bookname+"',writer='"+writer+"',translator='"+translator+"',publisher='"+publisher+"',date='"+date+"',price="+price+" where ISBN='"+ISBN+"'";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	//删除图书信息方法
	public static int Delbook(String ISBN){
		int i=0;
		try{
			String sql="delete from tb_bookInfo where ISBN='"+ISBN+"'";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		Dao.close();
		return i;
	}
	

	
	/*
	 * 对读者信息表的相关操作
	 */
	
	//添加读者 信息 
	public static int InsertReader(String name,String sex,String age,String identityCard,Date date,String maxNum,String tel,Double keepMoney,String zj,String zy,Date bztime,String ISBN){
		int i=0;
		try{
			String sql="insert into tb_reader(name,sex,age,identityCard,date,maxNum,tel,keepMoney,zj,zy,bztime,ISBN) values('"+name+"','"+sex+"','"+age+"','"+identityCard+"','"+date+"','"+maxNum+"','"+tel+"',"+keepMoney+",'"+zj+"','"+zy+"','"+bztime+"','"+ISBN+"')";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	//查询读者 信息 方法一
	public static List selectReader() {
		List list=new ArrayList();
		String sql = "select *  from tb_reader";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader=new Reader();
				//reader.setId(rs.getString("id"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setDate(rs.getDate("date"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setKeepMoney(rs.getDouble("keepMoney"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));
				reader.setBztime(rs.getDate("bztime"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	
	//查询读者 信息 方法二
	public static List selectReader(String readerISBN) {
		List list=new ArrayList();
		String sql = "select *  from tb_reader where ISBN='"+readerISBN+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader=new Reader();
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setDate(rs.getDate("date"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setKeepMoney(rs.getDouble("keepMoney"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));
				reader.setBztime(rs.getDate("bztime"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//修改读者信息
	public static int UpdateReader(String id,String name,String sex,String age,String identityCard,Date date,String maxNum,String tel,Double keepMoney,String zj,String zy,Date bztime,String ISBN){
		int i=0;
		try{
			String sql="update tb_reader set name='"+name+"',sex='"+sex+"',age='"+age+"',identityCard='"+identityCard+"',date='"+date+"',maxNum='"+maxNum+"',tel='"+tel+"',keepMoney="+keepMoney+",zj='"+zj+"',zy='"+zy+"',bztime='"+bztime+"'where ISBN='"+ISBN+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	//删除读者信息
	public static int DelReader(String ISBN){
		int i=0;
		try{
			String sql="delete from tb_reader where ISBN='"+ISBN+"'";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	/*
	 * 对订购信息表的操作
	 */
	
	//添加图书
	public static int InsertBookOrder(String ISBN,Date date,String number,String operator,String checkAndAccept,Double zk){
		int i=0;
		try{
			String sql="insert into tb_order(ISBN,date,number,operator,checkAndAccept,zk) values('"+ISBN+"','"+date+"','"+number+"','"+operator+"',"+checkAndAccept+",'"+zk+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	
	//查询图书订购  方法一
	public static List selectBookOrder() {
		List list=new ArrayList();
		String sql = "SELECT * FROM tb_order a INNER JOIN tb_bookInfo b ON a.ISBN = b.ISBN";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				OrderAndBookInfo order=new OrderAndBookInfo();
				order.setISBN(rs.getString("ISBN"));
				order.setOrderdate(rs.getDate("date"));
				order.setNumber(rs.getString("number"));
				order.setOperator(rs.getString("operator"));
				order.setCheckAndAccept(rs.getString("checkAndAccept"));
				order.setZk(rs.getDouble("zk"));
				order.setTypeId(rs.getString("typeId"));
				order.setBookname(rs.getString("bookName"));
				order.setWriter(rs.getString("writer"));
				order.setTraslator(rs.getString("translator"));
				order.setPublisher(rs.getString("publisher"));
				order.setDate(rs.getDate(12));
				order.setPrice(rs.getDouble("price"));
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//查询图书订购  方法二
	public static List selectBookOrder(String ISBN) {
		List list=new ArrayList();
		String sql = "SELECT * FROM tb_order where ISBN='"+ISBN+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Order order=new Order();
				order.setISBN(rs.getString("ISBN"));
				order.setDate(rs.getDate("date"));
				order.setNumber(rs.getString("number"));
				order.setOperator(rs.getString("operator"));
				order.setZk("zk");
				order.setCheckAndAccept("checkAndAccept");
				list.add(order);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//修改订购图书
	public static int UpdateCheckBookOrder(String ISBN){
		int i=0;
		try{
			String sql="update tb_order set checkAndAccept=0 where ISBN='"+ISBN+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	
	/*
	 * 对借阅的操作
	 */
	
	//添加借阅信息
	public static int InsertBookBorrow(String bookISBN,String readerISBN,String operatorId,Timestamp borrowDate,Timestamp backDate){
		int i=0;
		try{
			String sql="insert into tb_borrow(bookISBN,readerISBN,operatorId,borrowDate,backDate)values('"+bookISBN+"','"+readerISBN+"','"+operatorId+"','"+borrowDate+"','"+backDate+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	
	//查询借阅信息 
	public static List selectBorrow(String readerISBN) {
		List list=new ArrayList();
		String sql = "select *  from tb_borrow where readerISBN='"+readerISBN+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Borrow borrow=new Borrow();
				borrow.setId(rs.getInt("id"));
				borrow.setBookISBN(rs.getString("bookISBN"));
				borrow.setReaderISBN(rs.getString("readerISBN"));
				borrow.setBorrowDate(rs.getString("borrowDate"));
				borrow.setBackDate(rs.getString("backDate"));
				borrow.setBookName(rs.getString("borrowBookName"));
				list.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	/*
	 * 查询还书内容，tb_bookinfo tb_reader tb_borrow之间的查询
	 */
	public static List selectBookBack(String readerISBN) {
		List list=new ArrayList();
		String sql = "SELECT a.ISBN AS bookISBN, a.bookname, a.typeId ,b.id,b.operatorId, b.borrowDate, b.backDate, c.name AS readerName, c.ISBN AS readerISBN FROM tb_bookInfo a INNER JOIN tb_borrow b ON a.ISBN = b.bookISBN INNER JOIN tb_reader c ON b.readerISBN = c.ISBN WHERE (c.ISBN = '"+readerISBN+"' and isback=1)";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Back back=new Back();
				back.setBookISBN(rs.getString("bookISBN"));
				back.setBookname(rs.getString("bookname"));
				back.setTypeId(rs.getInt("typeId"));
				back.setOperatorId(rs.getString("operatorId"));
				back.setBorrowDate(rs.getString("borrowDate"));
				back.setBackDate(rs.getString("backDate"));
				back.setReaderName(rs.getString("readerName"));
				back.setReaderISBN(rs.getString("readerISBN"));
				back.setId(rs.getInt("id"));
				list.add(back);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	
	//归还图书
	public static int UpdateBookBack(String bookISBN,String readerISBN,int id){//归还图书操作
		int i=0;
		try{
			String sql="update tb_borrow set isback=0 where bookISBN='"+bookISBN+"'and readerISBN='"+readerISBN+"' and id="+id+"";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	
	//图书搜索
	public static List selectbookserch() {
		List list=new ArrayList();
		String sql = "select *  from tb_bookInfo";
		ResultSet s = Dao.executeQuery(sql);
		try {
			while (s.next()) {
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(s.getString("ISBN"));
				bookinfo.setTypeid(s.getString("typeId"));
				bookinfo.setBookname(s.getString("bookName"));
				bookinfo.setWriter(s.getString("writer"));
				bookinfo.setTranslator(s.getString("translator"));
				bookinfo.setPublisher(s.getString("publisher"));
				bookinfo.setDate(s.getDate("date"));
				bookinfo.setPrice(s.getDouble("price"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	
	public static List selectbookmohu(String bookname){
		List list=new ArrayList();
		String sql="select * from tb_bookInfo where bookname like '%"+bookname+"%'";
		System.out.print(sql);
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(s.getString(1));
				bookinfo.setTypeid(s.getString(2));
				bookinfo.setBookname(s.getString(3));
				bookinfo.setWriter(s.getString(4));
				bookinfo.setTranslator(s.getString(5));
				bookinfo.setPublisher(s.getString(6));
				bookinfo.setDate(s.getDate(7));
				bookinfo.setPrice(s.getDouble(8));
				list.add(bookinfo);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list;
		
		
	}
	
	
	public static List selectbookmohuwriter(String writer){
		List list=new ArrayList();
		String sql="select * from tb_bookInfo where writer like '%"+writer+"%'";
		System.out.print(sql);
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(s.getString("ISBN"));
				bookinfo.setTypeid(s.getString("typeId"));
				bookinfo.setBookname(s.getString("bookName"));
				bookinfo.setWriter(s.getString("writer"));
				bookinfo.setTranslator(s.getString("translator"));
				bookinfo.setPublisher(s.getString("publisher"));
				bookinfo.setDate(s.getDate("date"));
				bookinfo.setPrice(s.getDouble("price"));
				list.add(bookinfo);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list;
	}
	
	//添加管理员
	public static int Insertoperator(String name,String sex,int age,String identityCard,Date workdate,String tel,String password){
		int i=0;
		try{
			String sql="insert into tb_operator(name,sex,age,identityCard,workdate,tel,password) values('"+name+"','"+sex+"',"+age+",'"+identityCard+"','"+workdate+"','"+tel+"','"+password+"')";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	//查询管理员
	public static List selectuser() {
		List list=new ArrayList();
		String sql = "select id,name,sex,age,identityCard,workdate,tel,password  from tb_operator where admin=0";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				user user=new user();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSex(rs.getString(3));
				user.setAge(rs.getInt(4));
				user.setIdentityCard(rs.getString(5));
				user.setWorkdate(rs.getDate(6));
				user.setTel(rs.getString(7));
				user.setPassword(rs.getString(8));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//删除管理员
	public static int Deluser(int id){
		int i=0;
		try{
			String sql="delete from tb_operator where id='"+id+"'";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	//修改管理员信息
	public static int Updateuser(int id,String name,String sex,int age,String identityCard,Date workdate,String tel,String password){
		int i=0;
		try{
			String sql="update tb_operator set name='"+name+"',sex='"+sex+"',age="+age+",identityCard='"+identityCard+"',workdate='"+workdate+"',tel='"+tel+"',password='"+password+"' where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	//修改管理员登陆密码
	public static int Updatepass(String password,String name){
		int i=0;
		try{
			String sql="update tb_operator set password='"+password+"' where name='"+name+"'";
			
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	
	
	
	
	
	
	
	

}
