package com.sso.sso.service;

public interface UserService {

    void registerUser(String userName,String password);

    String userLogin(String userName,String password);

    String checkToken(String userName,String token);
}
