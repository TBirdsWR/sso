package com.sso.sso.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.sso.sso.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //设置key-value
    public void set(String key,Object value){
        String valueString = JSON.toJSONString(value);
        redisTemplate.opsForValue().set(key,valueString);
    }

    //设置key-value 加时长
    public void set(String key,Object value,int seconds){
        String valueString = JSON.toJSONString(value);
        redisTemplate.opsForValue().set(key,valueString,seconds, TimeUnit.SECONDS);
    }

    //获取value
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    //获取value转对象
    public Object get(String key,Class cls){
        String value = redisTemplate.opsForValue().get(key);
        return JSON.parseObject(value,cls);
    }

    //获取value转对象
    public void delete(String key){
        redisTemplate.delete(key);
    }

    public void expire(String key,int seconds) {
        redisTemplate.expire(key,seconds,TimeUnit.SECONDS);
    }


}
