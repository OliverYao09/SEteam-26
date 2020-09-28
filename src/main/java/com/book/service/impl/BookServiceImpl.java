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
 * (Book)表服务实现类
 *
 * @author makejava
 * @since 2020-09-26 10:04:33
 */
@Service("bookService")
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao bookDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Book queryById(Integer id) {
        return this.bookDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Book> queryAllByLimit(int offset, int limit) {
        return this.bookDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public Book insert(Book book) {
        this.bookDao.insert(book);
        return book;
    }

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public Book update(Book book) {
        this.bookDao.update(book);
        return this.queryById(book.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
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