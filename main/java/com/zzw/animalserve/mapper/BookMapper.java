package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Book;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    /**
     * 查询表book所有信息
     */
    List<Book> findAllBook();

    List<Book> findBookBySearched(String name);

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