package com.roden.java.database.oracle.jdbc;

import java.sql.*;

class EMP {
	private final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final String ORACLE_USER = "scott";
	private final String ORACLE_PWD = "oracle";
	private Connection conn;
	private Statement st;

	EMP() {
		try {
			Class.forName("oracle.jdbc.OracleDriver").newInstance();
			conn = DriverManager.getConnection(ORACLE_URL, ORACLE_USER,
					ORACLE_PWD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void delEmp() throws Exception {
		String sql = "delete emp where emp.empno=1000";
		st = conn.createStatement();
		int row = st.executeUpdate(sql);//创建、添加、修改、删除使用executeUpdate
		if (row > 0)
			System.out.println("删除成功");

	}

	void addEmp() throws SQLException {
		String sql = "insert into emp values(1000,'kyle','soft',"
				+ "1521,sysdate,1000,500,40)";
		st = conn.createStatement();
		int row = st.executeUpdate(sql);
		if (row > 0)
			System.out.println("添加成功");
	}

	void modifyEmp() throws SQLException {

		String sql = "update emp set emp.ename='admin' where emp.empno=1000";
		st = conn.createStatement();
		int row = st.executeUpdate(sql);
		if (row > 0)
			System.out.println("修改成功");

	}

}

public class JDBCExample {
	public static void main(String[] args) throws Exception {
		EMP emp = new EMP();
		emp.delEmp();
	}
}
