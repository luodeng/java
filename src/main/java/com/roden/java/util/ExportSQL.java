package com.roden.java.util;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ExportSQL {
	public static void main(String[] args) throws Exception {
		/*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:sqlserver://localhost:1433;databaseName=om", "autek",
				"********");
		String tableName = "om.dbo.om_pam_type";*/
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://AX-LinuxServer:3306/axshop_admin?useUnicode=true&characterEncoding=UTF-8", "root",
				"********");		
		String tableName = "AdminUser";
		
		DatabaseMetaData dbmd=conn.getMetaData();//获取数据库的元数据 
		System.out.println(dbmd.getURL()+dbmd.getUserName());		
		Statement stmt = conn.createStatement();		
		ResultSet rs = stmt.executeQuery("select * from  " + tableName);
		ResultSetMetaData rsmd = rs.getMetaData();//获取数据表的元数据		
		DateFormat df=new SimpleDateFormat("yyyy-hh-dd HH:mm:ss");
		PrintWriter pw=new PrintWriter("export.sql");
		StringBuilder sql=null;
		while (rs.next()) {
			sql = new StringBuilder("insert into " + tableName + " (");
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				sql.append(rsmd.getColumnName(i + 1) + ",");
			}
			//去掉最后一个,
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append( ") values(");
			for (int i = 0; i < rsmd.getColumnCount(); i++) {				
				if (rsmd.getColumnType(i + 1)==Types.VARCHAR) {
					sql.append( "'" + rs.getString(i + 1) + "',");
				} else if(rsmd.getColumnType(i + 1)==Types.TIMESTAMP){
					sql.append( "'" + df.format(rs.getDate(i + 1)) + "',");
				}else{//Integer 4 SMALLINT 5
					sql.append( rs.getString(i + 1) + ",");
				}				
			}
			sql.setLength(sql.length() - 1);
			sql.append( ")");
			String exportSql=sql.append("\n").toString();
			System.out.print(exportSql);
			pw.write(exportSql);
		}
		pw.close();
	}

}
