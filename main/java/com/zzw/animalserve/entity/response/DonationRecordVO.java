package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.DonationRecord;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/2__1:38
 */
@Data
public class DonationRecordVO {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String address;

    private Double amount;

    private Integer type;

    private Integer note;

    private Long trade;

    private String origin;

    private Long uid;

    private String username;

    private String createdAt;

    private String updatedAt;

    private Integer delectTag;

    public static DonationRecordVO entityToVO(DonationRecord donationRecord){
        DonationRecordVO donationRecordVO = new DonationRecordVO();
        donationRecordVO.setId(donationRecord.getDonationRecordId());
        donationRecordVO.setName(donationRecord.getDonationRecordName());
        donationRecordVO.setEmail(donationRecord.getDonationRecordEmail());
        donationRecordVO.setPhone(donationRecord.getDonationRecordPhone());
        donationRecordVO.setAddress(donationRecord.getDonationRecordAddress());
        donationRecordVO.setAmount(donationRecord.getDonationRecordAmount());
        donationRecordVO.setType(donationRecord.getDonationRecordType());
        donationRecordVO.setOrigin(donationRecord.getDonationRecordOriginName());
        donationRecordVO.setNote(donationRecord.getDonationRecordDenote());
        donationRecordVO.setTrade(donationRecord.getDonationRecordTrade());
        donationRecordVO.setUid(donationRecord.getCreateId());
        if(donationRecord.getCreateId() != 0){
            donationRecordVO.setUsername(donationRecord.getMember().getMemberName());
        }
        donationRecordVO.setCreatedAt(donationRecord.getCreateTime());
        donationRecordVO.setUpdatedAt(donationRecord.getUpdateTime());
        donationRecordVO.setDelectTag(donationRecord.getDelectTag());
        return donationRecordVO;
    }
}
