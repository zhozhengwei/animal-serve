package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * article_tag :
 */
@TableName(value = "article")
@Data
public class ArticleTag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Article_id,
     */
    private Long articleId;

    /**
     * Tag_id,
     */
    private Long tagId;

    /**
     * 一对一
     */
    @TableField(exist = false)
    private Tag tag;

}