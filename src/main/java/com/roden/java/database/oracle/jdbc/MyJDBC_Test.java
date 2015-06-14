package com.roden.java.database.oracle.jdbc;

import java.sql.*;


/**
 * jdbc协议连接数据库
 * 使用JDBC连接，可以跨平台，注意程序中一定要带上数据库提供的驱动包 *.jar
 * ORACLE驱动：oracle安装目录\product\10.1.0\db_*\jdbc\lib
 *
 *	一、注册驱动
 *  二、通过驱动管理对象[DriverManager]连接数据库
 *  三、建立语句集，准备发送SQL
 *  四，得到结果集，遍历取数据
 *  五、关闭相关连接
 */
public class MyJDBC_Test {
	private static Connection conn;//连接对象(代表连接上数据库)
	private static Statement st;//语句集对象(往数据库中发送SQL语句的对象)
	private static ResultSet rs;//结果集对象(用于接收结果)
	
	public static void main(String[] args) throws Exception {
		//注册驱动三种方式
		Class.forName("oracle.jdbc.OracleDriver").newInstance();	
		
		//连接数据库得到一个连接Connection对象,JDBC协议的语法：jdbc:数据库名称@数据库地址:端口号:数据库
		 conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", 
														"scott",
														"oracle");
		
		st = conn.createStatement();//根据连接好的连接对象创建一个发送SQL语句的对象
		rs = st.executeQuery("select * from emp");//通过语句对象发送SQL到数据库中执行，返回结果集
		//遍历结果
		while(rs.next()){//一行一行遍历			
			System.out.println(rs.getInt("EMPNO"));
			System.out.println(rs.getString("ename"));
			System.out.println(rs.getString("job"));
			System.out.println(rs.getInt("mgr"));
			System.out.println(rs.getDate("hiredate"));
			System.out.println(rs.getInt("sal"));
			System.out.println(rs.getInt("comm"));
			System.out.println(rs.getInt("deptno"));
			System.out.println("--------------------");
		}

		rs.close();
		st.close();
		conn.close();
	}

}
