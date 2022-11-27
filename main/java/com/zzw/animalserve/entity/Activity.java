package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * activity :
 */
@TableName(value = "activity")
@Data
public class Activity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Activity_id,
     */
    private Long activityId;

    /**
     * Activity_title,  活动主题
     *
     */
    private String activityTitle;

    /**
     * Activity_image,  活动封面
     */
    private String activityImage;
    /**
     * Activity_introduction,  活动简介
     */
    private String activityIntroduction;

    /**
     * Activity_main_content,  活动主体说明
     */
    private String activityMainContent;

    /**
     * Activity_address,  活动地址
     */
    private String activityAddress;

    /**
     * start_time,  开始时间
     */
    private Date startTime;

    /**
     * end_time,  结束时间
     */
    private Date endTime;

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
     * delect_tag,  逻辑删除：1(没有被删除)0代表已经被删除
     */
    @TableLogic
    private Integer delectTag;

    @TableField(exist = false)
    private Member member;


}