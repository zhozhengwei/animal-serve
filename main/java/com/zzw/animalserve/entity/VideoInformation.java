package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * video_information :
 */
@TableName(value = "video_information")
@Data
public class VideoInformation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Video_Information_id,
     */
    private Long videoInformationId;

    /**
     * Video_Information_url,  视频地址
     */
    private String videoInformationUrl;

    //Video_Information_poster

    /**
     * Video_Information_poster,  视频封面
     */
    private String videoInformationPoster;

    /**
     * Video_Information_content,  视频基本介绍
     */
    private String videoInformationContent;

    /**
     * Video_Information_type,  类型：1为网站自身的资源，0为第三方资源
     */
    private Integer videoInformationType;

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