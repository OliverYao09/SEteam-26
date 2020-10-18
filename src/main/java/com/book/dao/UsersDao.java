package com.book.dao;

import com.book.entity.Users;

import java.util.List;

/**
 * users Table database access layer
 *
 * @author makejava
 * @since 2020-09-26 10:04:32
 */
public interface UsersDao {

    Users login(Users users);

    Integer findUserByName(String username);

    void regist(Users users);

    List<Users> findUsers(Integer id);

    void delUserById(Integer id);
}