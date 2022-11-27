package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * article_comment :
 */
@TableName(value = "article_comment")
@Data
public class ArticleComment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Article_id,
     */
    private Long articleId;

    /**
     * Comment_id,
     */
    private Long commentId;

    /**
     * 一对一
     */
    @TableField(exist = false)
    private Comment comment;


}