package com.sso.sso.service.impl;

import com.sso.sso.pojo.User;
import com.sso.sso.util.RedisUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith( SpringRunner.class )
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testUserLogin(){
        String token = userServiceImpl.userLogin("小明","123456");
        System.out.println(token);
        Assert.assertNotEquals(null,token);
    }

    @Test
    public void testRegister(){
       userServiceImpl.registerUser("小红","123456");
    }


    @Test
    public void testCheckLogin(){
//        User user = (User)redisUtil.get("6ffe9555-77d5-4af2-8d90-3f46a018856f小明", User.class);
//        System.out.println(user);
        String token = userServiceImpl.checkToken("小明","6ffe9555-77d5-4af2-8d90-3f46a018856f");

        Assert.assertEquals("6ffe9555-77d5-4af2-8d90-3f46a018856f",token);
    }
}