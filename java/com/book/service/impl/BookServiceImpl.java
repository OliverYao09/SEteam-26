package com.book.service.impl;

import com.book.dao.BookDao;
import com.book.entity.Book;
import com.book.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Book)table service implement class
 *
 * @author makejava
 * @since 2020-09-26 10:04:33
 */
@Service("bookService")
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao bookDao;

    /**
     * query single record via ID
     *
     * @param id PK
     * @return Instance object
     */
    @Override
    public Book queryById(Integer id) {
        return this.bookDao.queryById(id);
    }

    /**
     * query multiple records
     *
     * @param offset query offsite
     * @param limit  query records number
     * @return objects list
     */
    @Override
    public List<Book> queryAllByLimit(int offset, int limit) {
        return this.bookDao.queryAllByLimit(offset, limit);
    }

    /**
     * insert data
     *
     * @param book Instance object
     * @return Instance object
     */
    @Override
    public Book insert(Book book) {
        this.bookDao.insert(book);
        return book;
    }

    /**
     * modify data
     *
     * @param book Instance object
     * @return Instance object
     */
    @Override
    public Book update(Book book) {
        this.bookDao.update(book);
        return this.queryById(book.getId());
    }

    /**
     * delete data via PK
     *
     * @param id PK
     * @return SuccessFlag
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.bookDao.deleteById(id) > 0;
    }

    @Override
    public List<Book> findByCondition(String con) {
        return bookDao.findByCondition(con);
    }

    @Override
    public List<Book> findByManyCon(String time1, String time2, String field, String operator, String value) {
        Map<String,String> map = new HashMap<>();
        map.put("time1",time1);
        map.put("time2",time2);
        map.put("field",field);
        map.put("operator",operator);
        map.put("value",value);
        return bookDao.findByManyCon(map);
    }
}