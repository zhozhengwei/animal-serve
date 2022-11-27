package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Book;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/26__18:33
 */
@Data
public class BookVO {

    private Long id;

    private String name;

    private String cover;

    private String url;

    private String introduction;

    private String createdAt;

    private String updatedAt;

    private Long uid;

    private Integer delectTag;

    public static BookVO entityToVO(Book book){
        BookVO bookVO = new BookVO();
        bookVO.setId(book.getBookId());
        bookVO.setName(book.getBookName());
        bookVO.setCover(book.getBookImage());
        bookVO.setUrl(book.getBookUrl());
        bookVO.setIntroduction(book.getBookIntroduction());
        bookVO.setCreatedAt(TimeFormat.formatTime(book.getCreateTime()));
        bookVO.setUpdatedAt(TimeFormat.formatTime(book.getUpdateTime()));
        bookVO.setUid(book.getCreateId());
        bookVO.setDelectTag(book.getDelectTag());
        return bookVO;
    }
}
