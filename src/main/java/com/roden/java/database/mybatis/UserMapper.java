package com.roden.java.database.mybatis;

import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	 @Select("select * from mybatis.user where id=#{id}")  
	public User findById(String Id);
}
