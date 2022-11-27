package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * donation_record :
 */
@TableName(value = "donation_record")
@Data
public class DonationRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 : Donation_Record_id,
     */
    private Long donationRecordId;

    /**
     * Donation_Record_name,  捐献者的姓名
     */
    private String donationRecordName;

    /**
     * Donation_Record_email,  捐献者的邮箱
     */
    private String donationRecordEmail;

    /**
     * Donation_Record_phone,  捐献者的电话
     */
    private String donationRecordPhone;

    /**
     * Donation_Record_address,  捐献者的地址
     */
    private String donationRecordAddress;

    /**
     * Donation_Record_amount,  捐献金额
     */
    private Double donationRecordAmount;

    /**
     * Donation_Record_type,  捐献类型（个人或者是组织）
     */
    private Integer donationRecordType;

    /**
     * Donation_Record_denote, 是否支付（1为已支付，0为未支付）
     */
    private Integer donationRecordDenote;

    /**
     * Donation_Record_trade, 是否支付（1为已支付，0为未支付）
     */
    private Long donationRecordTrade;

    /**
     * Donation_Record_originname,  捐献组织名
     */
    private String donationRecordOriginName;

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
     * delect_tag,  1(没有被删除)0代表已经被删除
     */
    @TableLogic
    private Integer delectTag;

    @TableField(exist = false)
    private Member member;


}