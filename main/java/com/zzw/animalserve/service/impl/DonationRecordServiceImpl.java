package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.DonationRecord;
import com.zzw.animalserve.mapper.DonationRecordMapper;
import com.zzw.animalserve.service.DonationRecordService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class DonationRecordServiceImpl extends ServiceImpl<DonationRecordMapper, DonationRecord> implements DonationRecordService {

    @Autowired
    private DonationRecordMapper donationRecordMapper;

    /**
     * 查询表donation_record所有信息
     */
    @Override
    public List<DonationRecord> findAllDonationRecord() {
        return donationRecordMapper.findAllDonationRecord();
    }

    /**
     * 根据主键donationRecordId查询表donation_record信息
     *
     * @param donationRecordId
     */
    @Override
    public DonationRecord findDonationRecordBydonationRecordId(@Param("donationRecordId") Long donationRecordId) {
        return donationRecordMapper.findDonationRecordBydonationRecordId(donationRecordId);
    }

    /**
     * 根据条件查询表donation_record信息
     *
     * @param donationRecord
     */
    @Override
    public List<DonationRecord> findDonationRecordByCondition(DonationRecord donationRecord) {
        return donationRecordMapper.findDonationRecordByCondition(donationRecord);
    }

    /**
     * 根据主键donationRecordId查询表donation_record信息
     *
     * @param donationRecordId
     */
    @Override
    public Integer deleteDonationRecordBydonationRecordId(@Param("donationRecordId") Long donationRecordId) {
        return donationRecordMapper.deleteDonationRecordBydonationRecordId(donationRecordId);
    }

    /**
     * 根据主键donationRecordId更新表donation_record信息
     *
     * @param donationRecord
     */
    @Override
    public Integer updateDonationRecordBydonationRecordId(DonationRecord donationRecord) {
        return donationRecordMapper.updateDonationRecordBydonationRecordId(donationRecord);
    }

    /**
     * 新增表donation_record信息
     *
     * @param donationRecord
     */
    @Override
    public Integer addDonationRecord(DonationRecord donationRecord) {
        return donationRecordMapper.addDonationRecord(donationRecord);
    }

}