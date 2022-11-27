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
 * bos :
 */
@TableName(value = "bos")
@Data
public class Bos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Bos_id,
     */
    private Long bosId;

    /**
     * Bos_name,  属的名称
     */
    private String bosName;

    /**
     * Bovidae_id,  属于XX科
     */
    private Long bovidaeId;

    /**
     * ARTIODACTYLA_id,  属于XX目
     */
    private Long artiodactylaId;

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
     *一对一
     */
    @TableField(exist = false)
    private Artiodactyla artiodactyla;

    /**
     *一对一
     */
    @TableField(exist = false)
    private Bovidae bovidae;

    /**
     *一对多 多个动物
     */
    @TableField(exist = false)
    private List<Biology> biologyList;


}