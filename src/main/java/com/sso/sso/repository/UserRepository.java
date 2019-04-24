package com.sso.sso.repository;

import com.sso.sso.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUserName(String userName);
}
