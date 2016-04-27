package com.roden.java.database.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
	@Select("select * from user where id=#{id}")  
	public User findById(String Id);
	 
	@Select("select * from user where id=#{id}")  
	public User findByStringId(int Id);
	
	
	    @Insert("insert into user(id,name,age) values(#{id},#{name}, #{age})")  
	    public int add(User user);  
	      
	    @Delete("delete from user where id = #{id}")  
	    public int deleteById(int id);  
	      
	    @Update("update user set name = #{name}, age = #{age} where id = #{id}")  
	    public int update(User user);  
	      
	    @Select("select * from user where id = #{id}")  
	    public User getUserById(int id);  
	      
	    @Select("select * from user")  
	    public List<User> getAllUsers();  
}
