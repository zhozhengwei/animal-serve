package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.entity.ApplyMember;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.mapper.ApplyMemberMapper;
import com.zzw.animalserve.service.ApplyMemberService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class ApplyMemberServiceImpl extends ServiceImpl<ApplyMemberMapper, ApplyMember> implements ApplyMemberService {

    @Autowired(required = false)
    private ApplyMemberMapper applyMemberMapper;

    /**
     * 查询表apply_member所有信息
     */
    @Override
    public List<ApplyMember> findAllApplyMember() {
        return applyMemberMapper.findAllApplyMember();
    }

    /**
     * 根据主键applyMemberId查询表apply_member信息
     *
     * @param applyMemberId
     */
    @Override
    public ApplyMember findApplyMemberByapplyMemberId(@Param("applyMemberId") Long applyMemberId) {
        return applyMemberMapper.findApplyMemberByapplyMemberId(applyMemberId);
    }

    /**
     * 根据条件查询表apply_member信息
     *
     * @param applyMember
     */
    @Override
    public List<ApplyMember> findApplyMemberByCondition(ApplyMember applyMember) {
        return applyMemberMapper.findApplyMemberByCondition(applyMember);
    }

    /**
     * 根据主键applyMemberId查询表apply_member信息
     *
     * @param applyMemberId
     */
    @Override
    public Integer deleteApplyMemberByapplyMemberId(@Param("applyMemberId") Long applyMemberId) {
        return applyMemberMapper.deleteApplyMemberByapplyMemberId(applyMemberId);
    }

    /**
     * 根据主键applyMemberId更新表apply_member信息
     *
     * @param applyMember
     */
    @Override
    public Integer updateApplyMemberByapplyMemberId(ApplyMember applyMember) {
        return applyMemberMapper.updateApplyMemberByapplyMemberId(applyMember);
    }

    /**
     * 新增表apply_member信息
     *
     * @param applyMember
     */
    @Override
    public Integer addApplyMember(ApplyMember applyMember) {
        QueryWrapper<ApplyMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Apply_Member_email", applyMember.getApplyMemberEmail());
        long count = applyMemberMapper.selectCount(queryWrapper);
        if(count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "申请邮箱重复！");
        }
        return applyMemberMapper.addApplyMember(applyMember);
    }



}