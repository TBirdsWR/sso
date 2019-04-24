package com.sso.sso.service.impl;

import com.sso.sso.pojo.User;
import com.sso.sso.repository.UserRepository;
import com.sso.sso.service.UserService;
import com.sso.sso.util.RedisUtil;
import io.lettuce.core.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserRepository repository;

    @Override
    public void registerUser(String userName,String password) {
        User user = new User(userName,DigestUtils.md5DigestAsHex(password.getBytes()));
        repository.save(user);
    }


    @Override
    public String userLogin(String userName,String password) {
        String token = String.valueOf(UUID.randomUUID());
        User user = repository.findUserByUserName(userName);
        try{
            if(null !=user && user.getUserPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
                redisUtil.set(token+":"+user.getUserName(),user,5*60);
                return token;
            }
        }catch (Exception e){
            return "登录失败";
        }
        return "登录失败";

    }

    @Override
    public String checkToken(String userName, String token) {
        String key = token+":"+userName;
        Object obj = redisUtil.get(key,User.class);
        if(null != obj && ((User)obj).getUserName().equals(userName)){
            redisUtil.expire(key,5*60);
            return token;
        }
        return "登录过期，请重新登录";
    }
}
