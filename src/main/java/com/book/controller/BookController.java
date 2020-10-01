package com.book.controller;

import com.book.entity.Book;
import com.book.service.BookService;
import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Book)table control layer
 *
 * @author makejava
 * @since 2020-09-26 10:04:33
 */
@Controller
@RequestMapping("book")
public class BookController {
    /**
     * Service object
     */
    @Resource
    private BookService bookService;

    /**
     * Query single data by primary key
     *
     * @param id Primary Key
     * @return Single data
     */
    @GetMapping("selectOne")
    public Book selectOne(Integer id) {
        return this.bookService.queryById(id);
    }

    /**
     * Add new books method
     * @param book
     * @return
     */
    @PostMapping("addBook")
    public String addBook(Book book) {
        Book book2 = bookService.insert(book);
        return "submit";
    }

    /**
     * Delete books method
     * @param id
     * @return
     */
    @DeleteMapping("delBook")
    @ResponseBody
    public String delBook(Integer id) {
        boolean flag = bookService.deleteById(id);
        return "";
    }

    /**
     * Query according to title author year or DOI

     * @return
     */
    @GetMapping("findByCondition")
    public String findByCondition(Map<String,Object> map,String con){
        List<Book> books = bookService.findByCondition(con);
        map.put("books", books);
        return "search";
    }

    /**
     * Multi-condition query
     * @param map
     * @return
     */
    @GetMapping("findByManyCon")
    public String findByManyCon(Map<String,Object> map,String time1,String time2,
                                String field,String operator,String value){
        List<Book> books = bookService.findByManyCon(time1,time2,field,operator,value);
        map.put("books", books);
        return "search";
    }

}