package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * animalia :
 */
@TableName(value = "animalia")
@Data
public class Animalia extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Animalia_id,
     */
    private Long animaliaId;

    /**
     * Animalia_name,  界名
     */
    private String animaliaName;

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

    /**
     * 一对多的门类
     */
    @TableField(exist = false)
    private List<Chordata> chordataList;

}