package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * apply_volunteer :
 */
@TableName(value = "Apply_Volunteer")
@Data
public class ApplyVolunteer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Apply_Volunteer_id,
     */
    private Long applyVolunteerId;

    /**
     * Apply_Volunteer_name,  申请者的姓名
     */
    private String applyVolunteerName;

    /**
     * Apply_Volunteer_email,  申请者的邮箱
     */
    private String applyVolunteerEmail;

    /**
     * Apply_Volunteer_address,  申请者的地址
     */
    private String applyVolunteerAddress;

    /**
     * Apply_Volunteer_introduction,  申请者的自我介绍
     */
    private String applyVolunteerIntroduction;

    /**
     * Apply_Volunteer_skill_introduction,  申请者的技能自我介绍
     */
    private String applyVolunteerSkillIntroduction;

    /**
     * Apply_Volunteer_status,  申请的状态：
     */
    private Integer applyVolunteerStatus;

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

    @TableField(exist = false)
    private Member member;


}