package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Book;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/2__19:26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "图书名")
    private String name;

    @ApiModelProperty(value= "封面")
    private String cover;

    @ApiModelProperty(value= "地址")
    private String url;

    @ApiModelProperty(value= "描述")
    private String introduction;

    @ApiModelProperty(value= "管理员ID")
    private Long uid;

    @ApiModelProperty(value= "逻辑删除")
    private Integer delectTag;

    public Book toEntity() {
        Book book = new Book();
        book.setBookId(id);
        book.setBookName(name);
        book.setBookImage(cover);
        book.setBookUrl(url);
        book.setBookIntroduction(introduction);
        book.setCreateId(uid);
        book.setDelectTag(delectTag);
        return book;
    }
}
