package com.roden.java.database.oracle.jdbc;

import java.sql.*;

//JDBC使用

class JDBC {
	public static void main(String[] args) throws Exception {
		Connection con;
		Statement st;
		ResultSet rs;
		Class.forName("oracle.jdbc.OracleDriver").newInstance();
		con = DriverManager
				.getConnection("jdbc:oracle:thin:@localhost:1521:roden",
						"scott", "tiger");

		st = con.createStatement();
		rs = st.executeQuery("select * from emp");

		while (rs.next()) {
			System.out.println(rs.getString("ENAME"));

		}

	}

}
