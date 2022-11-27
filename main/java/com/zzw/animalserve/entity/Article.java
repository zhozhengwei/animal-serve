package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * article :
 */
@TableName(value = "article")
@Data
public class Article extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Article_id,
     */
    private Long articleId;

    /**
     * Article_title,  主题
     */
    private String articleTitle;

    /**
     * Member_id,  用户
     */
    private Long memberId;

    /**
     * Article_cover,  封面
     */
    private String articleCover;

    /**
     * Article_introduction,  简单介绍
     */
    private String articleIntroduction;

    /**
     * Article_text,  文本内容
     */
    private String articleText;

    /**
     * Article_attribute,  1(为新闻)0为博客
     */
    private Integer articleAttribute;

    /**
     * Article_collects_count,  收藏统计
     */
    private Long articleCollectsCount;

    /**
     * Article_comments_count,  评论次数
     */
    private Long articleCommentsCount;

    /**
     * Article_look_count,  被查看的次数
     */
    private Long articleLookCount;

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
     * delect_tag,  1(已经被删除)0代表没有被删除
     */
    @TableLogic
    private Integer delectTag;

    /**
     *一对一
     */
    @TableField(exist = false)
    private Member member;



    /**
     * 一对多 标签
     */
    @TableField(exist = false)
    private List<ArticleTag> tagList;
}