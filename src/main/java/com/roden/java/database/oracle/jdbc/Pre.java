package com.roden.java.database.oracle.jdbc;

import java.sql.*;

//预编译,其中WWW.DOTA.COM为本机IP;
//DatebaseMetaDate类为获取数据库信息的类;
public class Pre {
	public static void main(String[] args) throws Exception {
		String sql = "select * from emp where empno=?";
		int empno = 7499;
		Class.forName("oracle.jdbc.OracleDriver").newInstance();
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@www.dota.com:1521:orcl", "scott", "oracle");

		DatabaseMetaData mt = con.getMetaData();
		System.out.println(mt.getDriverName());

		PreparedStatement st = con.prepareStatement(sql);
		// Statement st= con.createStatement(); //对比;
		st.setInt(1, empno);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getString(2));
		}
	}
}