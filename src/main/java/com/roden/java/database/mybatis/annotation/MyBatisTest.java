package com.roden.java.database.mybatis.annotation;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
/**
 * myBatis数据库连接测试 
 */
public class MyBatisTest {
	/**
	 * 获得MyBatis SqlSessionFactory  
	 * SqlSessionFactory负责创建SqlSession，一旦创建成功，就可以用SqlSession实例来执行映射语句，commit，rollback，close等方法。
	 * @return
	 */
	private SqlSessionFactory sessionFactory;
	
	@Before
	public void getSessionFactory() {		
		String resource = "com/roden/java/database/mybatis/annotation/configuration.xml";		
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	//加载UserMapper.java
	//注解方式（简单sql使用）
	@Test
	public void ann() {
		SqlSession sqlSession = sessionFactory.openSession();		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);	
		
		User user = userMapper.findById(1);
		System.out.println(user);		
		
		user = userMapper.findByStringId("1");
		System.out.println(user);
	}
}
