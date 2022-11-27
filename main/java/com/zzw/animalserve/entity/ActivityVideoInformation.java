package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * activity_video_information :
 */
@TableName(value = "activity_video_information")
@Data
public class ActivityVideoInformation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Video_Information_id,
     */
    private Long videoInformationId;

    /**
     * Activity_id,
     */
    private Long activityId;

    /**
     * 一对一
     */
    @TableField(exist = false)
    private Activity activity;

    @TableField(exist = false)
    private VideoInformation videoInformation;


}