package com.book.service;

import com.book.entity.Book;

import java.util.List;


/**
 * (Book)table service interface
 *
 * @author makejava
 * @since 2020-09-26 10:04:32
 */
public interface BookService {

    /**
     * query single record via ID
     *
     * @param id PK
     * @return Instance object
     */
    Book queryById(Integer id);

    /**
     * query multiple records
     *
     * @param offset query offsite
     * @param limit  query records number
     * @return objects list
     */
    List<Book> queryAllByLimit(int offset, int limit);

    /**
     * insert data
     *
     * @param book Instance object
     * @return Instance object
     */
    Book insert(Book book);

    /**
     * modify data
     *
     * @param book Instance object
     * @return Instance object
     */
    Book update(Book book);

    /**
     * delete data via PK
     *
     * @param id PK
     * @return SuccessFlag
     */
    boolean deleteById(Integer id);

    List<Book> findByCondition(String con);

    List<Book> findByManyCon(String time1, String time2, String field, String operator, String value);
}