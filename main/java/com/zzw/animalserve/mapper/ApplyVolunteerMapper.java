package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.ApplyVolunteer;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ApplyVolunteerMapper extends BaseMapper<ApplyVolunteer> {

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