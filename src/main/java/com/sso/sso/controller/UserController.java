package com.sso.sso.controller;

import com.sso.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(String userName,String password){
        userService.registerUser(userName,password);
        return "Ok";
    }

    @RequestMapping("/checkLogin")
    public String checkLogin(String userName, String token){
        return userService.checkToken(userName,token);
    }

    @RequestMapping("/login")
    public String login(String userName, String password){
        String result = userService.userLogin(userName,password);
        return result;
    }
}
