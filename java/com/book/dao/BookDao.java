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
     * Query single data by ID
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
     * Query by entity as filter condition
     *
     * @param book Instance object
     * @return Object list
     */
    List<Book> queryAll(Book book);

    /**
     * adding data
     *
     * @param book Instance object
     * @return Number of rows affected
     */
    int insert(Book book);

    /**
     * edditing
     *
     * @param book Instance object
     * @return Number of rows affected
     */
    int update(Book book);

    /**
     * Delete data through primary key
     *
     * @param id PK
     * @return Number of rows affected
     */
    int deleteById(Integer id);

    List<Book> findByCondition(@Param("con") String con);

    List<Book> findByManyCon(Map<String, String> map);
}