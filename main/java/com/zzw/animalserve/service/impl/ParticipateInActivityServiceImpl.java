package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.entity.ParticipateInActivity;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.mapper.ParticipateInActivityMapper;
import com.zzw.animalserve.service.ParticipateInActivityService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class ParticipateInActivityServiceImpl extends ServiceImpl<ParticipateInActivityMapper, ParticipateInActivity> implements ParticipateInActivityService {

    @Autowired
    private ParticipateInActivityMapper participateInActivityMapper;

    /**
     * 查询表participate_in_activity所有信息
     */
    @Override
    public List<ParticipateInActivity> findAllParticipateInActivity() {
        return participateInActivityMapper.findAllParticipateInActivity();
    }

    /**
     * 根据主键participateInActivityId查询表participate_in_activity信息
     *
     * @param participateInActivityId
     */
    @Override
    public ParticipateInActivity findParticipateInActivityByparticipateInActivityId(@Param("participateInActivityId") Long participateInActivityId) {
        return participateInActivityMapper.findParticipateInActivityByparticipateInActivityId(participateInActivityId);
    }

    /**
     * 根据条件查询表participate_in_activity信息
     *
     * @param participateInActivity
     */
    @Override
    public List<ParticipateInActivity> findParticipateInActivityByCondition(ParticipateInActivity participateInActivity) {
        return participateInActivityMapper.findParticipateInActivityByCondition(participateInActivity);
    }

    /**
     * 根据主键participateInActivityId查询表participate_in_activity信息
     *
     * @param participateInActivityId
     */
    @Override
    public Integer deleteParticipateInActivityByparticipateInActivityId(@Param("participateInActivityId") Long participateInActivityId) {
        return participateInActivityMapper.deleteParticipateInActivityByparticipateInActivityId(participateInActivityId);
    }

    /**
     * 根据主键participateInActivityId更新表participate_in_activity信息
     *
     * @param participateInActivity
     */
    @Override
    public Integer updateParticipateInActivityByparticipateInActivityId(ParticipateInActivity participateInActivity) {
        return participateInActivityMapper.updateParticipateInActivityByparticipateInActivityId(participateInActivity);
    }

    /**
     * 新增表participate_in_activity信息
     *
     * @param participateInActivity
     */
    @Override
    public Integer addParticipateInActivity(ParticipateInActivity participateInActivity) {
        QueryWrapper<ParticipateInActivity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("participate_in_activity_email", participateInActivity.getParticipateInActivityEmail());
        long count = participateInActivityMapper.selectCount(queryWrapper);
        QueryWrapper<ParticipateInActivity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.eq("Activity_id", participateInActivity.getActivityId());
        long count1 = participateInActivityMapper.selectCount(queryWrapper);
        if(count > 0 && count1 > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "不能重复报名同一个活动！");
        }
        return participateInActivityMapper.addParticipateInActivity(participateInActivity);
    }

}