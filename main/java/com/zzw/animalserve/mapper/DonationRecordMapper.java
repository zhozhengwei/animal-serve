package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.DonationRecord;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DonationRecordMapper extends BaseMapper<DonationRecord> {
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

    DonationRecord findDonationRecordBydonationTrade(@Param("donationRecordTrade") Long donationRecordTrade);

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