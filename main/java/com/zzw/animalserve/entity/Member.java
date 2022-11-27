package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * (Member)实体类
 *
 * @author makejava
 * @since 2022-10-09 14:37:54
 */
@TableName(value = "member")
@Data
public class Member extends BaseEntity {
    @TableField(exist = false)
    private static final long serialVersionUID = -38957667551892002L;


    private Long memberId;
    /**
     * 用户名
     */
    private String memberName;
    /**
     * 邮箱
     */
    private String memberEmail;
    /**
     * 手机号
     */
    private String memberPhone;
    /**
     * 密码
     */
    private String password;
    /**
     * 1(男)或者0(女)（NULL(未知)）
     */
    private String memberSex;
    /**
     * 默认的动态头像（生成一个随机的头像）
     */
    private String memberImage;
    /**
     * 地址
     */
    private String memberAddress;
    /**
     * 年龄
     */
    private Integer memberAge;
    /**
     * 三级身份认证（1->超级，2->次级，3->普通）
     */
    private Integer memberRole;
    /**
     * 志愿者1（非志愿者默认0）
     */
    private Integer memberIdentity;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 1(已经被删除)0代表没有被删除
     */
    @TableLogic
    private Integer delectTag;

    @TableField(exist = false)
    private String token;

    //一对多的属性映射，一个用户有多个角色
    @TableField(exist = false)
    private List<Role> roles = new ArrayList<>();


}

