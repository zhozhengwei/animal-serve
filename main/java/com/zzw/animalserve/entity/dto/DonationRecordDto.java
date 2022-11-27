package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.DonationRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/23__13:33
 */

@ApiModel(value = "捐献记录")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationRecordDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "捐献者姓名")
    private String name;

    @ApiModelProperty(value= "邮箱")
    private String email;

    @ApiModelProperty(value= "电话")
    private String phone;

    @ApiModelProperty(value= "地址")
    private String address;

    @ApiModelProperty(value= "数量")
    private Double amount;

    @ApiModelProperty(value= "支付类型")
    private Integer type;

    @ApiModelProperty(value= "订单号")
    private Long trade;

    @ApiModelProperty(value= "组织名")
    private String orginName;

    @ApiModelProperty(value= "是否支付成功")
    private Integer note;

    @ApiModelProperty(value= "是否为会员")
    private Long uid;

    public DonationRecord toEntity() {
        DonationRecord toEntity = new DonationRecord();
        toEntity.setDonationRecordId(id);
        toEntity.setDonationRecordName(name);
        toEntity.setDonationRecordEmail(email);
        toEntity.setDonationRecordPhone(phone);
        toEntity.setDonationRecordAddress(address);
        toEntity.setDonationRecordAmount(amount);
        toEntity.setDonationRecordDenote(note);
        toEntity.setDonationRecordTrade(trade);
        toEntity.setDonationRecordType(type);
        toEntity.setDonationRecordOriginName(orginName);
        toEntity.setCreateId(uid);
        return toEntity;
    }
}
