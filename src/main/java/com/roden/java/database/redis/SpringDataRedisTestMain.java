package com.roden.java.database.redis;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class SpringDataRedisTestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/roden/java/database/redis/spring-context2.xml");
        RedisTemplate redisTemplate = (RedisTemplate) context.getBean("jedisTemplate");
        // 其中key采取了StringRedisSerializer
        // 其中value采取JdkSerializationRedisSerializer
        ValueOperations<String, User> valueOper = redisTemplate.opsForValue();
        User u1 = new User(1, "zhangsan", "helo");
        User u2 = new User(2, "lisi", "25");
        valueOper.set("u:u1", u1);
        redisTemplate.opsForValue().set("u:u2", u2);
        System.out.println(valueOper.get("u:u1").getName());
        System.out.println(valueOper.get("u:u2").getClass().getName());
        redisTemplate.delete("u:u2");
    }
}