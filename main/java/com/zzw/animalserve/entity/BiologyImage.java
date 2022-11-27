package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * biology_image :
 */
@TableName(value = "biology_image")
@Data
public class BiologyImage extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : biology_id,
     */
    private Long biologyId;

    /**
     * Image_Information_id,  图片库
     */
    private Long imageInformationId;


    /**
     * 一对一
     * 对应图片信息
     */
    @TableField(exist = false)
    private ImageInformation imageInformation;

}