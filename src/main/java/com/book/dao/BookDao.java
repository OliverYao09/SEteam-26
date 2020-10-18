package com.book.dao;

import com.book.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (Book)Table database access layer
 *
 * @author makejava
 * @since 2020-09-26 10:04:32
 */
public interface BookDao {

    /**
     * Query a single data by ID
     *
     * @param id Primary key
     * @return Instance object
     */
    Book queryById(Integer id);

    /**
     * Query specified row data
     *
     * @param offset Query start position
     * @param limit  Number of queries
     * @return Object list
     */
    List<Book> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * Query by entity as a filter
     *
     * @param book Instance object
     * @return Object list
     */
    List<Book> queryAll(Book book);

    /**
     * Add data
     *
     * @param book Instance object
     * @return Number of affected rows
     */
    int insert(Book book);

    /**
     * Change data
     *
     * @param book Instance object
     * @return Number of affected rows
     */
    int update(Book book);

    /**
     * Delete data by primary key
     *
     * @param id primary key
     * @return Number of affected rows
     */
    int deleteById(Integer id);

    List<Book> findByCondition(@Param("con") String con);

    List<Book> findByManyCon(Map<String, String> map);
}