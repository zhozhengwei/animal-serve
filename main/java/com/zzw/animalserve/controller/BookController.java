package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Book;
import com.zzw.animalserve.entity.dto.BookDto;
import com.zzw.animalserve.entity.response.BookVO;
import com.zzw.animalserve.entity.response.InterlinkageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.BookService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "图书信息")
@RestController
@RequestMapping("/book")
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;

    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("图书列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:message:lists')")
    public BaseResponse<List<BookVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            List<Book> allBook = bookService.findAllBook();
            List<BookVO> bookVOList = new ArrayList<>();
            for (Book temp: allBook) {
                if(temp.getDelectTag() == 0){
                    BookVO bookVO = BookVO.entityToVO(temp);
                    bookVOList.add(bookVO);
                }
            }
            return ResultUtils.success(bookVOList);
        }else{
            Book book = new Book();
            book.setBookName(name);
            List<Book> bookByCondition = bookService.findBookByCondition(book);
            List<BookVO> bookVOList = new ArrayList<>();
            for (Book temp: bookByCondition) {
                if(temp.getDelectTag() == 0){
                    BookVO bookVO = BookVO.entityToVO(temp);
                    bookVOList.add(bookVO);
                }
            }
            return ResultUtils.success(bookVOList);
        }
    }

    @ApiOperation("条件查询")
    @PostMapping("/listBook")
//    @PreAuthorize("hasAuthority('sys:message:lists')")
    public BaseResponse<List<BookVO>> queryByBook(@RequestBody BookDto bookDto) {
        Book book = bookDto.toEntity();
        List<Book> bookByCondition = bookService.findBookByCondition(book);
        List<BookVO> bookVOList = new ArrayList<>();
        for (Book temp: bookByCondition) {
            if(temp.getDelectTag() == 0){
                BookVO bookVO = BookVO.entityToVO(temp);
                bookVOList.add(bookVO);
            }
        }
        return ResultUtils.success(bookVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    /**
     * 分页查询
     * @param pageNum
     * @return
     */
    @ApiOperation("分页列表")
    @GetMapping("/listAll/{pageNum}")
//    @PreAuthorize("hasAuthority('sys:link:lists')")
    public BaseResponse<PageInfo<BookVO>> queryByPagefindAll(@PathVariable("pageNum") Integer pageNum) {
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<BookVO> pageList = bookService.findPageList(pageNum);
        return ResultUtils.success(pageList);
    }

    @ApiOperation("前台查询")
    @PostMapping("/listSearch")
//    @PreAuthorize("hasAuthority('sys:link:lists')")
    public BaseResponse<PageInfo<BookVO>> queryByPageSearch(Integer pageNum, @RequestBody BookDto bookDto) {
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<BookVO> pageList = bookService.findPageSearch(pageNum, bookDto.getName());
        return ResultUtils.success(pageList);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单个图书")
    @GetMapping("/searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:book:sigln')")
    public BaseResponse<BookVO> queryById(@PathVariable("id") Long id) {
        Book bookBybookId = this.bookService.findBookBybookId(id);
        BookVO bookVO = BookVO.entityToVO(bookBybookId);
        return ResultUtils.success(bookVO);
    }

    /**
     * 新增数据
     *
     * @param bookDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增图书")
    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('sys:book:save')")
    public BaseResponse<Integer> add(@RequestBody BookDto bookDto) {
        Book book = bookDto.toEntity();
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        book.setDelectTag(0);
        return ResultUtils.success(this.bookService.addBook(book));
    }

    /**
     * 编辑数据
     *
     * @param bookDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑图书信息")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:book:update')")
    public BaseResponse<Integer> edit(@RequestBody BookDto bookDto) {
        Book book = bookDto.toEntity();
        System.out.println("=======book======>"+book.toString());
        book.setUpdateTime(new Date());
        return ResultUtils.success(this.bookService.updateBookBybookId(book));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除图书")
    @DeleteMapping("/delete")
    //@PreAuthorize("hasAuthority('sys:book:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Book applyMember = bookService.findBookBybookId(id);
        applyMember.setDelectTag(1);
        return ResultUtils.success(this.bookService.updateBookBybookId(applyMember));
    }


}