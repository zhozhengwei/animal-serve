package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Book;
import com.zzw.animalserve.entity.response.BookVO;
import com.zzw.animalserve.mapper.BookMapper;
import com.zzw.animalserve.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private BookMapper bookMapper;

    /**
     * 分页查询
     */
    @Override
    public PageInfo<BookVO> findPageList(int pageNum) {
        PageHelper.startPage(pageNum, 9);
        List<Book> allBook = bookMapper.findAllBook();
        PageInfo pageResult = new PageInfo(allBook);
        List<BookVO> bookVOList = new ArrayList<>();
        for (Book temp: allBook){
            if(temp.getDelectTag() == 0){
                BookVO bookVO = BookVO.entityToVO(temp);
                bookVOList.add(bookVO);
            }
        }
        pageResult.setList(bookVOList);
        return pageResult;
    }


    @Override
    public PageInfo<BookVO> findPageSearch(int pageNum, String name) {
        PageHelper.startPage(pageNum, 9);
        List<Book> allBook = bookMapper.findBookBySearched(name);
        PageInfo pageResult = new PageInfo(allBook);
        List<BookVO> bookVOList = new ArrayList<>();
        for (Book temp: allBook){
            if(temp.getDelectTag() == 0){
                BookVO bookVO = BookVO.entityToVO(temp);
                bookVOList.add(bookVO);
            }
        }
        pageResult.setList(bookVOList);
        return pageResult;
    }

    /**
     * 查询表book所有信息
     */
    @Override
    public List<Book> findAllBook() {
        return bookMapper.findAllBook();
    }

    /**
     * 根据主键bookId查询表book信息
     *
     * @param bookId
     */
    @Override
    public Book findBookBybookId(@Param("bookId") Long bookId) {
        return bookMapper.findBookBybookId(bookId);
    }

    /**
     * 根据条件查询表book信息
     *
     * @param book
     */
    @Override
    public List<Book> findBookByCondition(Book book) {
        return bookMapper.findBookByCondition(book);
    }

    /**
     * 根据主键bookId查询表book信息
     *
     * @param bookId
     */
    @Override
    public Integer deleteBookBybookId(@Param("bookId") Long bookId) {
        return bookMapper.deleteBookBybookId(bookId);
    }

    /**
     * 根据主键bookId更新表book信息
     *
     * @param book
     */
    @Override
    public Integer updateBookBybookId(Book book) {
        return bookMapper.updateBookBybookId(book);
    }

    /**
     * 新增表book信息
     *
     * @param book
     */
    @Override
    public Integer addBook(Book book) {
        return bookMapper.addBook(book);
    }

}