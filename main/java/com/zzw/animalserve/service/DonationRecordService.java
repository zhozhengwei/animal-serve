package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzw.animalserve.entity.DonationRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DonationRecordService extends IService<DonationRecord> {

    /**
     * 查询表donation_record所有信息
     */
    List<DonationRecord> findAllDonationRecord();

    /**
     * 根据主键donationRecordId查询表donation_record信息
     *
     * @param donationRecordId
     */
    DonationRecord findDonationRecordBydonationRecordId(@Param("donationRecordId") Long donationRecordId);

    /**
     * 根据条件查询表donation_record信息
     *
     * @param donationRecord
     */
    List<DonationRecord> findDonationRecordByCondition(DonationRecord donationRecord);

    /**
     * 根据主键donationRecordId查询表donation_record信息
     *
     * @param donationRecordId
     */
    Integer deleteDonationRecordBydonationRecordId(@Param("donationRecordId") Long donationRecordId);

    /**
     * 根据主键donationRecordId更新表donation_record信息
     *
     * @param donationRecord
     */
    Integer updateDonationRecordBydonationRecordId(DonationRecord donationRecord);

    /**
     * 新增表donation_record信息
     *
     * @param donationRecord
     */
    Integer addDonationRecord(DonationRecord donationRecord);
}