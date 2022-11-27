package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* message : 
*/
@Data
@TableName(value = "message")
public class Message  extends BaseEntity {

    private static final long serialVersionUID = 1L;

        /**
        * 主键 : Message_id,  
        */
        private Long messageId;

        /**
        * Message_name,  消息发起人的姓名
        */
        private String messageName;

        /**
        * Message_email,  消息发起人的邮箱
        */
        private String messageEmail;

        /**
        * Message_text,  消息发起人的主体消息
        */
        private String messageText;

        /**
         * parent_id,  管理员回答
         */
        private Long parentId;

        /**
        * Message_status,  消息是什么人的类型1为会员发送（0为非会员（游客）发送）
        */
        private Integer messageStatus;

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


        @TableField(exist = false)
        private Message message;


        @TableField(exist = false)
        private Member member;

}