package com.roden.java.database.oracle.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Facade外观模式：为某些特定功能提供一个对外的接口，类似代理(proxy)模式
 * @author kyle
 * 2012-10-29
 */
public class JDBCFacade {
	private final String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	private final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final String ORACLE_USER = "scott";
	private final String ORACLE_PWD = "oracle";
	
	private Connection conn;
	private Statement st;
	private String sql;
	private ResultSet rs;
	
	JDBCFacade(String sql){
		try {
			Class.forName(ORACLE_DRIVER).newInstance();
			conn = DriverManager.getConnection(ORACLE_URL, ORACLE_USER,
					ORACLE_PWD);
			st = conn.createStatement();
			this.sql=sql;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	boolean addEmp() throws Exception{
		try {
			return st.executeUpdate(sql)>0?true : false;			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			st.close();
			conn.close();
		}
		return false;
	}
	
	boolean delEmp() throws Exception{
		try {
			return st.executeUpdate(sql)>0?true : false;			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			st.close();
			conn.close();
		}
		return false;
	}
	
	List queryEmp() throws Exception{
		try {
			ArrayList list = new ArrayList();
			rs = st.executeQuery(sql);//查询语句使用executeQuery
			while(rs.next()){//一行一行遍历		
				//把每行都装在EmpInfo对象中，最后再讲每个EmpInfo对象存到ArrayList中
				EmpInfo emp = new EmpInfo(rs.getInt("EMPNO"), 
						rs.getString("ename"),
						rs.getString("job"),
						rs.getInt("mgr"),
						rs.getDate("hiredate"),
						rs.getInt("sal"),
						rs.getInt("comm"),
						rs.getInt("deptno"));
				
				list.add(emp);
			}
			return list;			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{			
			rs.close();
			st.close();
			conn.close();
		}
		return null;
	}

	class EmpInfo{
		private int empno;
		private String ename;
		private String job;
		private int mgr;
		private java.sql.Date date;
		private int sal;
		private int comm;
		private int deptno;
		
		public EmpInfo(int empno, String ename, String job, int mgr, Date date,
				int sal, int comm, int deptno) {
			this.empno = empno;
			this.ename = ename;
			this.job = job;
			this.mgr = mgr;
			this.date = date;
			this.sal = sal;
			this.comm = comm;
			this.deptno = deptno;
		}
}
	
	
	
	
	public static void main(String[] args) throws Exception {
		String sql = "";
		JDBCFacade jdbc =  new JDBCFacade(sql);//统一的对外调用(接口)
		jdbc.addEmp();
	}

}
