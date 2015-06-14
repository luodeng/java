package com.roden.java.database.oracle.jdbc;

import java.sql.*;

//import oracle.jdbc.OracleDriver;

//import sun.jdbc.odbc.JdbcOdbcDriver;

/**
 * 第一种驱动方式
 * JDBC-ODBC协议连接数据库
 * 直接连接到ODBC，程序中不用带上驱动包
 * @author kyle
 * 2012-10-29
 */
public class MyJDBC_ODBC_Test {
	private static Connection conn;//连接对象(代表连接上数据库)
	private static Statement st;//语句集对象(往数据库中发送SQL语句的对象)
	private static ResultSet rs;//结果集对象(用于接收结果)

	public static void main(String[] args) throws Exception {
		//注册驱动三种方式  ------ODBC无需注册
		//JdbcOdbcDriver driver = new JdbcOdbcDriver();
		//OracleDriver driver = new OracleDriver();
		//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
	//DriverManager.registerDriver(new JdbcOdbcDriver());
		
		
		
		//连接数据库得到一个连接Connection对象
		 conn = DriverManager.getConnection("jdbc:odbc:JODBC", 
														"scott",
														"tiger");
		
		st = conn.createStatement();//根据连接好的连接对象创建一个发送SQL语句的对象
		rs = st.executeQuery("SELECT * FROM EMP");//通过语句对象发送SQL到数据库中执行，返回结果集
		//遍历结果
		while(rs.next()){//一行一行遍历
			
//			System.out.println(rs.getInt(1));
			System.out.println(rs.getInt("EMPNO"));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
			System.out.println(rs.getInt(4));
			System.out.println(rs.getDate(5));
			System.out.println(rs.getInt(6));
			System.out.println(rs.getInt(7));
			System.out.println(rs.getInt(8));
			System.out.println("--------------------");
		}
		rs.close();
		st.close();
		conn.close();
		
		

	}

}
