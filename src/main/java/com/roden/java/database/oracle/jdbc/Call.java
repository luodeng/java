package com.roden.java.database.oracle.jdbc;

import java.sql.*;

public class Call {

	/**
	 * 调用存储过程
	 */
	public static void main(String[] args) throws Exception {
		// JDBC必须要注册驱动,导入数剧库提供的类库到项目下进行注册;
		// Class.forName("oracle.jdbc.OracleDriver").newInstance();
		// Connection con=DriverManager.getConnection
		// ("jdbc:oracle:thin:@192.168.1.111:1521:orcl","scott","tiger");
		// 用ODBC无需注册驱动,直接连接
		Connection con = DriverManager.getConnection("jdbc:odbc:JODBC",
				"scott", "oracle");

		Statement st = con.createStatement();
		// st.executeUpdate("INSERT INTO EMP VALUES(8001,'张五','Dotaer', 7788, TO_DATE('1936-12-12','yyyy-mm-dd'), 5000,400,20)");
		// st.executeUpdate("DELETE FROM EMP WHERE EMPNO=8001");
		// st.executeUpdate("UPDATE EMP SET JOB='DOTAER' WHERE EMPNO=8001" );
		ResultSet rs = st.executeQuery("select * from emp");

		while (rs.next()) {
			System.out.println(rs.getString(2));
		}
		CallableStatement cs = con.prepareCall("{call proc_emp_login(?,?,?)}");
		cs.setInt(1, 7369);
		cs.setString(2, "SMITH");
		cs.registerOutParameter(3, Types.INTEGER);
		cs.execute();
		System.out.println(cs.getInt(3));
		cs.close();
		rs.close();
		st.close();
		con.close();
	}
}

/**
 * CallableStatement：调用存储过程 例子：登录
 * 
 * create or replace procedure proc_emp_login (emp_no in scott.emp.empno%type,
 * emp_name in scott.emp.ename%type, loginstate out number) is begin select
 * count(*) into loginstate from scott.emp where scott.emp.empno=emp_no and
 * scott.emp.ename=emp_name; end proc_emp_login;
 */

