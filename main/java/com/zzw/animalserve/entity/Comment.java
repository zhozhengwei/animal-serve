package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * comment :
 */
@TableName(value = "comment")
@Data
public class Comment  extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Comment_id,
     */
    private Long commentId;

    /**
     * Comment_content,  评论的内容
     */
    private String commentContent;

    /**
     * Member_id,  评论的用户
     */
    private Long memberId;

    /**
     * Parent_cmt_id,  多个子级对一个父级
     */
    private Long parentCmtId;

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
     * delect_tag,  逻辑删除：1(没有被删除)0代表已经被删除
     */
    @TableLogic
    private Integer delectTag;

    /**
     * 一对一
     */
    @TableField(exist = false)
    private Member member;

    @TableField(exist = false)
    private List<Comment> children;


}