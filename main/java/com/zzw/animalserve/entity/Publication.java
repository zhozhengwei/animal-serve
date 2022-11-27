package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * publication :
 */
@Data
@TableName(value = "publication")
public class Publication extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Publication_id,
     */
    private Long publicationId;

    /**
     * Publication_name,  刊物名称
     */
    private String publicationName;

    /**
     * Publication_image,  刊物封面
     */
    private String publicationImage;

    /**
     * Publication_url,  刊物地址
     */
    private String publicationUrl;

    /**
     * Publication_introduction,  刊物描述
     */
    private String publicationIntroduction;

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


}