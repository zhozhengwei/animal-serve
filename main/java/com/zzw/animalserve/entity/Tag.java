package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tag :
 */
@TableName(value = "tag")
@Data
public class Tag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Tag_id,
     */
    private Long tagId;

    /**
     * Tag_title,  标签主题
     */
    private String tagTitle;

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


}