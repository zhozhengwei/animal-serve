package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.entity.ApplyVolunteer;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.mapper.ApplyVolunteerMapper;
import com.zzw.animalserve.service.ApplyVolunteerService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class ApplyVolunteerServiceImpl extends ServiceImpl<ApplyVolunteerMapper, ApplyVolunteer> implements ApplyVolunteerService {

    @Autowired
    private ApplyVolunteerMapper applyVolunteerMapper;

    /**
     * 查询表apply_volunteer所有信息
     */
    @Override
    public List<ApplyVolunteer> findAllApplyVolunteer() {
        return applyVolunteerMapper.findAllApplyVolunteer();
    }

    /**
     * 根据主键applyVolunteerId查询表apply_volunteer信息
     * @param applyVolunteerId
     */
    @Override
    public ApplyVolunteer findApplyVolunteerByapplyVolunteerId(@Param("applyVolunteerId") Long applyVolunteerId) {
        return applyVolunteerMapper.findApplyVolunteerByapplyVolunteerId(applyVolunteerId);
    }

    /**
     * 根据条件查询表apply_volunteer信息
     * @param applyVolunteer
     */
    @Override
    public List<ApplyVolunteer> findApplyVolunteerByCondition(ApplyVolunteer applyVolunteer) {
        return applyVolunteerMapper.findApplyVolunteerByCondition(applyVolunteer);
    }

    /**
     * 根据主键applyVolunteerId查询表apply_volunteer信息
     * @param applyVolunteerId
     */
    @Override
    public Integer deleteApplyVolunteerByapplyVolunteerId(@Param("applyVolunteerId") Long applyVolunteerId) {
        return applyVolunteerMapper.deleteApplyVolunteerByapplyVolunteerId(applyVolunteerId);
    }

    /**
     * 根据主键applyVolunteerId更新表apply_volunteer信息
     * @param applyVolunteer
     */
    @Override
    public Integer updateApplyVolunteerByapplyVolunteerId(ApplyVolunteer applyVolunteer) {
        return applyVolunteerMapper.updateApplyVolunteerByapplyVolunteerId(applyVolunteer);
    }

    /**
     * 新增表apply_volunteer信息
     * @param applyVolunteer
     */
    @Override
    public Integer addApplyVolunteer(ApplyVolunteer applyVolunteer) {
        //账户不能重复
        QueryWrapper<ApplyVolunteer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Apply_Volunteer_email", applyVolunteer.getApplyVolunteerEmail());
        long count = applyVolunteerMapper.selectCount(queryWrapper);
        if(count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "申请邮箱重复！");
        }
        return applyVolunteerMapper.addApplyVolunteer(applyVolunteer);
    }

}