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
 * (Book)表控制层
 *
 * @author makejava
 * @since 2020-09-26 10:04:33
 */
@Controller
@RequestMapping("book")
public class BookController {
    /**
     * 服务对象
     */
    @Resource
    private BookService bookService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Book selectOne(Integer id) {
        return this.bookService.queryById(id);
    }

    /**
     * 新增书书籍
     * @param book
     * @return
     */
    @PostMapping("addBook")
    public String addBook(Book book) {
        Book book2 = bookService.insert(book);
        return "submit";
    }

    /**
     * 删除书籍
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
     * 根据 title author year or doi查询
     * @return
     */
    @GetMapping("findByCondition")
    public String findByCondition(Map<String,Object> map,String con){
        List<Book> books = bookService.findByCondition(con);
        map.put("books", books);
        return "search";
    }

    /**
     * 多条件查询
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