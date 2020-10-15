package com.book.service;

import com.book.entity.Users;

import java.util.List;

/**
 * user service
 */
public interface UsersService {

    Users login(Users users);

    Integer findUserByName(String username);

    void regist(Users users);

    List<Users> findUsers(Integer id);

    void delUserById(Integer id);
}