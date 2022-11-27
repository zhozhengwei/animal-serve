package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.ParticipateInActivity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ParticipateInActivityMapper extends BaseMapper<ParticipateInActivity> {

    /**
     * 查询表participate_in_activity所有信息
     */
    List<ParticipateInActivity> findAllParticipateInActivity();

    /**
     * 根据主键participateInActivityId查询表participate_in_activity信息
     *
     * @param participateInActivityId
     */
    ParticipateInActivity findParticipateInActivityByparticipateInActivityId(@Param("participateInActivityId") Long participateInActivityId);

    /**
     * 根据条件查询表participate_in_activity信息
     *
     * @param participateInActivity
     */
    List<ParticipateInActivity> findParticipateInActivityByCondition(ParticipateInActivity participateInActivity);

    /**
     * 根据主键participateInActivityId查询表participate_in_activity信息
     *
     * @param participateInActivityId
     */
    Integer deleteParticipateInActivityByparticipateInActivityId(@Param("participateInActivityId") Long participateInActivityId);

    /**
     * 根据主键participateInActivityId更新表participate_in_activity信息
     *
     * @param participateInActivity
     */
    Integer updateParticipateInActivityByparticipateInActivityId(ParticipateInActivity participateInActivity);

    /**
     * 新增表participate_in_activity信息
     *
     * @param participateInActivity
     */
    Integer addParticipateInActivity(ParticipateInActivity participateInActivity);

}