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
 * chordata :
 */
@TableName(value = "chordata")
@Data
public class Chordata extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Chordata__id,
     */
    private Long chordataId;

    /**
     * Chordata_name,  门的名称
     */
    private String chordataName;

    /**
     * Animalia_id,  属于动物界
     */
    private Long animaliaId;

    /**
     * assort_sit_id,  一个区域内部的门类（list）
     */
    private Long assortSitId;

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
     * 一对一的界
     */
    @TableField(exist = false)
    private Animalia animalia;

    /**
     * 一对多的纲
     */
    @TableField(exist = false)
    private List<Mammalia> mammaliaList;

}