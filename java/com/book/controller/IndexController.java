package com.book.controller;

import com.book.entity.Book;
import com.book.entity.Users;
import com.book.service.BookService;
import com.book.service.UsersService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author makejava
 * @since 2020-09-26 10:04:33
 */
@Controller
public class IndexController {

    @Resource
    private BookService bookService;
    @Resource
    private UsersService usersService;

    @GetMapping("/")
    public String search(Map<String, Object> map) {
        List<Book> books = bookService.findByCondition("");
        map.put("books", books);
        return "search";
    }

    @GetMapping("/submit")
    public String submit() {
        return "submit";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
        return "search";
    }

    @PostMapping("/userLogin")
    public String userLogin(HttpServletRequest req,Map<String, Object> map, Users users) {
        Users user = usersService.login(users);
        if(null == user){
            req.setAttribute("msg", "Wrong username or password！");
            return login();
        }
        req.getSession().setAttribute("user", user);
        return search(map);
    }

    @PostMapping("/registUser")
    public String registUser(HttpServletRequest req,Map<String, Object> map, Users users) {
        Integer count = usersService.findUserByName(users.getUsername());
        if(count != null && count > 0){
            map.put("msg","This username has been registered！");
        } else {
            users.setRole("Customer");
            usersService.regist(users);
            map.put("msg", "Registration Success！");
        }
        return regist();
    }

    @GetMapping("/regist")
    public String regist() {
        return "regist";
    }

    @GetMapping("/toUser")
    public String toUser(HttpServletRequest req,Map<String, Object> map) {
        Users user = (Users) req.getSession().getAttribute("user");
        Integer id = -1;
        if(user != null){
            id = user.getId();
        }
        List<Users> us = usersService.findUsers(id);
        map.put("us",us);
        return "users";
    }

    @PostMapping("/delUser")
    @ResponseBody
    public String delUser(HttpServletRequest req,Map<String, Object> map, Integer id) {
        if(!StringUtils.isEmpty(id)){
            usersService.delUserById(id);
        }
        return "";
    }

}