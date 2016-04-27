package com.roden.java.database.mybatis;

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
		String resource = "com/roden/java/database/mybatis/configuration.xml";		
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	//注解方式，加载UserMapper.java
	@Test
	public void ann() {
		SqlSession sqlSession = sessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = userMapper.findById("1");
		System.out.println(user);
		
		user = userMapper.findByStringId(2);
		System.out.println(user);

	}
	//传统xml方式，加载mapper.xml
	@Test
	public void xml() {
		SqlSession sqlSession = sessionFactory.openSession();
        User user = sqlSession.selectOne("user.findById", 1);
		System.out.println(user);	

	}
	
	
	
	//------------------------------------xml增删改查-------------------------------------------------
	@Test
	public void add() {
		//默认是手动的 
		SqlSession session = sessionFactory.openSession();		
		int rows = session.insert("user.insertUser", new User(4, "哈哈", 29));
		//提交
		session.commit();
		System.out.println(rows);
		session.close();
	}
	
	@Test
	public void update() {
		SqlSession session = sessionFactory.openSession();		
		session.update("user.updateUser", new User(4, "呵呵", 29));
		session.commit();
		session.close();
	}
	
	@Test
	public void delete() {
		SqlSession session = sessionFactory.openSession();		
		session.delete("user.deleteUser", 4);
		session.commit();
		session.close();
	}
	
	@Test
	public void getAllUser() {
		SqlSession session = sessionFactory.openSession();		
		List<User> list = session.selectList("user.getAllUser");
		System.out.println(list.size());
		session.close();
	}

}
