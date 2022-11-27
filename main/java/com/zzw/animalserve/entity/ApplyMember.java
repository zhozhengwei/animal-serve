package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* apply_member : 
*/
@TableName(value = "Apply_Member")
@Data
public class ApplyMember  extends BaseEntity {

private static final long serialVersionUID = 1L;

        /**
        * 主键 : Apply_Member_id,  
        */
        private Long applyMemberId;

        /**
        * Apply_Member_name,  申请人姓名
        */
        private String applyMemberName;

        /**
        * Apply_Member_email,  申请人邮箱
        */
        private String applyMemberEmail;

        /**
        * Apply_Member_address,  申请人地址
        */
        private String applyMemberAddress;

        /**
        * Apply_Member_phone,  申请人电话
        */
        private String applyMemberPhone;

        /**
        * Apply_Member_introduction,  申请人的自我介绍
        */
        private String applyMemberIntroduction;

        /**
        * Apply_Member_status,  申请的状态：0默认没有通过管理员同意，1正在取得联系审查中，2已经同意该用户可以注册账号了
        */
        private Integer applyMemberStatus;

        /**
        * create_time,  创建时间
        */
        private Date createTime;

        /**
        * update_time,  更新时间
        */
        private Date updateTime;

        /**
        * update_id,  更新人
        */
        private Long updateId;

        /**
        * delect_tag,  逻辑删除：1(没有被删除)0代表已经被删除
        */
        @TableLogic
        private Integer delectTag;

}