package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzw.animalserve.entity.ApplyVolunteer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplyVolunteerService extends IService<ApplyVolunteer> {

    /**
     * 查询表apply_volunteer所有信息
     */
    List<ApplyVolunteer> findAllApplyVolunteer();

    /**
     * 根据主键applyVolunteerId查询表apply_volunteer信息
     *
     * @param applyVolunteerId
     */
    ApplyVolunteer findApplyVolunteerByapplyVolunteerId(@Param("applyVolunteerId") Long applyVolunteerId);

    /**
     * 根据条件查询表apply_volunteer信息
     *
     * @param applyVolunteer
     */
    List<ApplyVolunteer> findApplyVolunteerByCondition(ApplyVolunteer applyVolunteer);

    /**
     * 根据主键applyVolunteerId查询表apply_volunteer信息
     *
     * @param applyVolunteerId
     */
    Integer deleteApplyVolunteerByapplyVolunteerId(@Param("applyVolunteerId") Long applyVolunteerId);

    /**
     * 根据主键applyVolunteerId更新表apply_volunteer信息
     *
     * @param applyVolunteer
     */
    Integer updateApplyVolunteerByapplyVolunteerId(ApplyVolunteer applyVolunteer);

    /**
     * 新增表apply_volunteer信息
     *
     * @param applyVolunteer
     */
    Integer addApplyVolunteer(ApplyVolunteer applyVolunteer);
}