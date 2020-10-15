package com.book.service.impl;

import com.book.dao.BookDao;
import com.book.dao.UsersDao;
import com.book.entity.Book;
import com.book.entity.Users;
import com.book.service.BookService;
import com.book.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * users service implement class
 *
 * @author makejava
 * @since 2020-09-26 10:04:33
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersDao usersDao;

    @Override
    public Users login(Users users) {
        return usersDao.login(users);
    }

    @Override
    public Integer findUserByName(String username) {
        return usersDao.findUserByName(username);
    }

    @Override
    public void regist(Users users) {
        usersDao.regist(users);
    }

    @Override
    public List<Users> findUsers(Integer id) {
        return usersDao.findUsers(id);
    }

    @Override
    public void delUserById(Integer id) {
        usersDao.delUserById(id);
    }
}