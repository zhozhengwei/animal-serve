package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * image_information :
 */
@TableName(value = "image_information")
@Data
public class ImageInformation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Image_Information_id,
     */
    private Long imageInformationId;

    /**
     * Image_Information_url,  图片地址
     */
    private String imageInformationUrl;

    /**
     * Image_Information_name,  图片名称
     */
    private String imageInformationName;

    /**
     * Image_Information_content,  图片的简单描述
     */
    private String imageInformationContent;

    /**
     * Image_status,  图片的状态
     */
    private Integer imageStatus;

    /**
     * Image_class,  0为普通图片；1为壁纸和图鉴
     */
    private String imageClass;

    /**
     * create_id,  创建人
     */
    private Long createId;

    /**
     * create_time,  创建时间
     */
    private String createTime;

    /**
     * update_id,  更新人
     */
    private Long updateId;

    /**
     * update_time,  更新时间
     */
    private String updateTime;

    /**
     * delect_tag,  逻辑删除：1(没有被删除)0代表已经被删除
     */
    @TableLogic
    private Integer delectTag;


}