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
 * artiodactyla :
 */
@TableName(value = "artiodactyla")
@Data
public class Artiodactyla extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : ARTIODACTYLA_id,
     */
    private Long artiodactylaId;

    /**
     * ARTIODACTYLA_name,  目的名称
     */
    private String artiodactylaName;

    /**
     * Mammalia_id,  属于XX纲
     */
    private Long mammaliaId;

    /**
     * Chordata_id,  属于XX门
     */
    private Long chordataId;

    /**
     * Animalia_id,  属于动物界
     */
    private Long animaliaId;

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
     *一对一
     */
    @TableField(exist = false)
    private Animalia animalia;

    /**
     *一对一
     */
    @TableField(exist = false)
    private Chordata chordata;

    /**
     *一对一
     */
    @TableField(exist = false)
    private Mammalia mammalia;


    /**
     *一对多
     */
    @TableField(exist = false)
    private List<Bovidae> bovidaeList;

}