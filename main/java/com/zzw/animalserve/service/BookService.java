package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Book;
import com.zzw.animalserve.entity.response.BookVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookService extends IService<Book> {

    PageInfo<BookVO> findPageList(int pageNum);

    PageInfo<BookVO> findPageSearch(int pageNum, String name);

    /**
     * 查询表book所有信息
     */
    List<Book> findAllBook();

    /**
     * 根据主键bookId查询表book信息
     *
     * @param bookId
     */
    Book findBookBybookId(@Param("bookId") Long bookId);

    /**
     * 根据条件查询表book信息
     *
     * @param book
     */
    List<Book> findBookByCondition(Book book);

    /**
     * 根据主键bookId查询表book信息
     *
     * @param bookId
     */
    Integer deleteBookBybookId(@Param("bookId") Long bookId);

    /**
     * 根据主键bookId更新表book信息
     *
     * @param book
     */
    Integer updateBookBybookId(Book book);

    /**
     * 新增表book信息
     *
     * @param book
     */
    Integer addBook(Book book);
}