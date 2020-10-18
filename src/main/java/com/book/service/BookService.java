package com.book.service;

import com.book.entity.Book;

import java.util.List;


/**
 * (Book)Table service interface
 *
 * @author makejava
 * @since 2020-09-26 10:04:32
 */
public interface BookService {

    /**
     * Query a single data by ID
     *
     * @param id primary key
     * @return Instance object
     */
    Book queryById(Integer id);

    /**
     * Query multiple data
     *
     * @param offset Query start position
     * @param limit  Number of queries
     * @return Object list
     */
    List<Book> queryAllByLimit(int offset, int limit);

    /**
     * add data
     *
     * @param book Instance object
     * @return Instance object
     */
    Book insert(Book book);

    /**
     * change data
     *
     * @param book Instance object
     * @return Instance object
     */
    Book update(Book book);

    /**
     * delete data by primary key
     *
     * @param id primary key
     * @return success or not
     */
    boolean deleteById(Integer id);

    List<Book> findByCondition(String con);

    List<Book> findByManyCon(String time1, String time2, String field, String operator, String value);
}