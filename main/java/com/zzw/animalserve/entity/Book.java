package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * book :
 */
@TableName(value = "book")
@Data
public class Book extends BaseEntity {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键 : book_id,
     */
    private Long bookId;

    /**
     * book_name,  推荐图书名字
     */
    private String bookName;

    /**
     * book_image,  推荐图书封面
     */
    private String bookImage;

    /**
     * book_url,  推书链接
     */
    private String bookUrl;

    /**
     * book_introduction,  推书描述
     */
    private String bookIntroduction;

    /**
     * create_id,  创建人
     */
    private Long createId;

    /**
     * create_time,  创建时间
     */
    private Date createTime;

    /**
     * update_id,  更新人
     */
    private Long updateId;

    /**
     * update_time,  更新时间
     */
    private Date updateTime;

    /**
     * delect_tag,  1(没有被删除)0代表已经被删除
     */
    @TableLogic
    private Integer delectTag;

}