package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.ApplyMember;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ApplyMemberMapper extends BaseMapper<ApplyMember> {

    /**
     * 查询表apply_member所有信息
     */
    List<ApplyMember> findAllApplyMember();

    /**
     * 根据主键applyMemberId查询表apply_member信息
     *
     * @param applyMemberId
     */
    ApplyMember findApplyMemberByapplyMemberId(@Param("applyMemberId") Long applyMemberId);

    /**
     * 根据条件查询表apply_member信息
     *
     * @param applyMember
     */
    List<ApplyMember> findApplyMemberByCondition(ApplyMember applyMember);

    /**
     * 根据主键applyMemberId查询表apply_member信息
     *
     * @param applyMemberId
     */
    Integer deleteApplyMemberByapplyMemberId(@Param("applyMemberId") Long applyMemberId);

    /**
     * 根据主键applyMemberId更新表apply_member信息
     *
     * @param applyMember
     */
    Integer updateApplyMemberByapplyMemberId(ApplyMember applyMember);

    /**
     * 新增表apply_member信息
     *
     * @param applyMember
     */
    Integer addApplyMember(ApplyMember applyMember);

}