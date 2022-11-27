package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * participate_in_activity :
 */
@TableName(value = "participate_in_activity")
@Data
public class ParticipateInActivity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : participate_in_activity_id,
     */
    private Long participateInActivityId;

    /**
     * Activity_id,  对应的活动
     */
    private Long activityId;

    /**
     * participate_in_activity_name,  参加活动用户的用户名
     */
    private String participateInActivityName;

    /**
     * participate_in_activity_email,  参加活动用户的邮箱
     */
    private String participateInActivityEmail;

    /**
     * participate_in_activity_status,  0为默认的未同意是否能参加，1已经同意在参加的人员名单中
     */
    private Integer participateInActivityStatus;

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
     * delect_tag,  逻辑删除
     */
    @TableLogic
    private Integer delectTag;

    /**
     * 一对一与活动的关系
     */
    @TableField(exist = false)
    private Activity activity;

    /**
     * 一对一用户关系
     */
    @TableField(exist = false)
    private Member member;

}