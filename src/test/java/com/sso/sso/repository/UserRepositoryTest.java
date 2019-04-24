package com.sso.sso.repository;

import com.sso.sso.pojo.User;
import com.sso.sso.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith( SpringRunner.class )
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository dao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void saveUser(){
        User user = dao.findUserByUserName("小明");
        System.out.println(user);

    }

    @Test
    public void testRedis(){
        redisUtil.set("user",new User("小",DigestUtils.md5DigestAsHex("123456".getBytes())),10);
//        while(redisUtil.get("user") != null){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(redisUtil.get("user"));
//        }
    }

    @Test
    public void testRedisFet(){
        System.out.println(redisUtil.get("小明",User.class));
    }

    @Test
    public void testRedisget(){
        redisUtil.set("小明",new User("小明",DigestUtils.md5DigestAsHex("123456".getBytes())));
        System.out.println(redisUtil.get("小明"));

        redisTemplate.opsForValue().set("test", 100,60*10,TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间

//        redisTemplate.boundValueOps("test").increment(-1);//val做-1操作

        redisTemplate.opsForValue().get("test");//根据key获取缓存中的val

//        redisTemplate.boundValueOps("test").increment(1);//val +1

        System.out.println(redisTemplate.getExpire("test"));//根据key获取过期时间

        System.out.println(redisTemplate.getExpire("test",TimeUnit.SECONDS));//根据key获取过期时间并换算成指定单位

        System.out.println(redisTemplate.delete("test"));//根据key删除缓存

        System.out.println( redisTemplate.hasKey("546545"));//检查key是否存在，返回boolean值

        System.out.println(redisTemplate.opsForSet().add("red_123", "1","2","3"));//向指定key中存放set集合

       // System.out.println(redisTemplate.expire("red_123",1000 , TimeUnit.MILLISECONDS));//设置过期时间
        System.out.println(redisTemplate.opsForSet().isMember("red_123", "1"));//根据key查看集合中是否存在指定数据
    }

    @Test
    public void testUUID(){
        System.out.println( String.valueOf(UUID.randomUUID()));
    }
}