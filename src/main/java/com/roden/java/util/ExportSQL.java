package com.roden.java.util;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;

public class ExportSQL {
	public static void main(String[] args) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:sqlserver://localhost:1433;databaseName=om", "autek",
				"FLYVIDEO");
		DatabaseMetaData dbmd=conn.getMetaData();//获取数据库的元数据 
		System.out.println(dbmd.getURL()+dbmd.getUserName());		
		Statement stmt = conn.createStatement();
		String tableName = "om.dbo.om_pam_type";
		ResultSet rs = stmt.executeQuery("select * from  " + tableName);
		ResultSetMetaData rsmd = rs.getMetaData();//获取数据表的元数据
		StringBuffer exportSQL=new StringBuffer("");
		while (rs.next()) {
			String sql = "insert into " + tableName + " (";
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				sql += rsmd.getColumnName(i + 1) + ",";
			}
			sql = sql.substring(0, sql.length() - 1);
			sql += ") values(";
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				if (rsmd.getColumnType(i + 1)==Types.VARCHAR) {
					sql += "'" + rs.getString(i + 1) + "',";
				} else {
					sql += rs.getString(i + 1) + ",";
				}				
			}
			sql = sql.substring(0, sql.length() - 1);
			sql += ")";
			exportSQL.append(sql).append("\n");			
		}
		PrintWriter pw=new PrintWriter("export.sql");
		System.out.println(exportSQL);
		pw.write(exportSQL.toString());
		pw.close();
	}

}
