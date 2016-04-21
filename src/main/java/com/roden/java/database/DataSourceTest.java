package com.roden.java.database;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
 


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource; 

public class DataSourceTest {  
	 HikariDataSource hikariDataSource=null;
	 ComboPooledDataSource comboPooledDataSource=null;
   
    public Connection getHikariConnection(){
    	if(hikariDataSource==null){
    		//连接池配置
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("com.mysql.jdbc.Driver");
            config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/testdb?user=root&password=123456&useUnicode=true&characterEncoding=utf8");
            config.addDataSourceProperty("cachePrepStmts", true);
            config.addDataSourceProperty("prepStmtCacheSize", 500);
            config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
            config.setConnectionTestQuery("SELECT 1");
            config.setAutoCommit(true);
            //池中最小空闲链接数量
            config.setMinimumIdle(10);
            //池中最大链接数量
            config.setMaximumPoolSize(50);             
            hikariDataSource= new HikariDataSource(config);
    	}        
        Connection conn=null;
        try {
			 conn= hikariDataSource.getConnection();
		} catch (SQLException e) {			
			e.printStackTrace();
			hikariDataSource.resumePool();	
			return null;
		}
        return conn;
       // hikariDataSource.close();//关闭连接池
         
    }
    
    public Connection getC3p0Connection(){
    	if(comboPooledDataSource==null){
    		comboPooledDataSource=new ComboPooledDataSource();
    		try {
    			comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
    		} catch (PropertyVetoException e) {			
    			e.printStackTrace();
    			return null;
    		}
    		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/roden?useUnicode=true&characterEncoding=UTF-8");
    		comboPooledDataSource.setUser("root");
    		comboPooledDataSource.setPassword("");
    		comboPooledDataSource.setMaxPoolSize(20);
    		comboPooledDataSource.setMinPoolSize(5);
    		comboPooledDataSource.setInitialPoolSize(10);
    		comboPooledDataSource.setMaxStatements(100);	
    	}    		
		Connection conn=null;
		try {
			conn = comboPooledDataSource.getConnection();
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		}
		return conn;
    }   
    
     
   
     
    public static void main(String[] args) throws SQLException {
        DataSourceTest dst = new DataSourceTest();
        Connection hikari = dst.getHikariConnection();    
        Connection c3p0 = dst.getC3p0Connection(); 
        
        
        //最后关闭链接
        hikari.close();
        c3p0.close();
        
    }
     
}