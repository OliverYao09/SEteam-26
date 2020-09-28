package com.book.controller;

import com.book.entity.Book;
import com.book.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


}