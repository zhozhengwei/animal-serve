package com.zzw.animalserve.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.ApplyMember;
import com.zzw.animalserve.entity.dto.ApplyMemberDto;
import com.zzw.animalserve.entity.response.ApplyMemberVO;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.service.ApplyMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import cn.hutool.core.util.StrUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * (ApplyMember)表控制层
 * 为申请成为会员的功能
 * @author makejava
 * @since 2022-10-13 09:50:21
 */
@Api(tags = "申请会员")
@RestController
@RequestMapping("applyMember")
@Slf4j
public class ApplyMemberController extends BaseController {




    /**
     * 服务对象
     */
    @Resource
    private ApplyMemberService applyMemberService;

    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("申请列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:apply:lists')")
    public BaseResponse<List<ApplyMemberVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            List<ApplyMember> allApplyMember = applyMemberService.findAllApplyMember();
            List<ApplyMemberVO> applyMemberVOList = new ArrayList<>();
            for (ApplyMember temp: allApplyMember) {
                if(temp.getDelectTag() == 0){
                    ApplyMemberVO applyMemberVO = ApplyMemberVO.entityToVO(temp);
                    applyMemberVOList.add(applyMemberVO);
                }
            }
            return ResultUtils.success(applyMemberVOList);
        }else{
            ApplyMember applyMember = new ApplyMember();
            applyMember.setApplyMemberName(name);
            List<ApplyMember> applyMemberByCondition = applyMemberService.findApplyMemberByCondition(applyMember);
            List<ApplyMemberVO> applyMemberVOList = new ArrayList<>();
            for (ApplyMember temp: applyMemberByCondition) {
                if(temp.getDelectTag() == 0){
                    ApplyMemberVO applyMemberVO = ApplyMemberVO.entityToVO(temp);
                    applyMemberVOList.add(applyMemberVO);
                }

            }
            return ResultUtils.success(applyMemberVOList);
        }
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
//    @PreAuthorize("hasAuthority('sys:apply:lists')")
    public BaseResponse<List<ApplyMemberVO>> queryBySearch(@RequestBody ApplyMemberDto applyMemberDto) {
        ApplyMember applyMember = applyMemberDto.toEntity();
        List<ApplyMember> applyMemberByCondition = applyMemberService.findApplyMemberByCondition(applyMember);
        List<ApplyMemberVO> applyMemberVOList = new ArrayList<>();
        for (ApplyMember temp: applyMemberByCondition) {
            ApplyMemberVO applyMemberVO = ApplyMemberVO.entityToVO(temp);
            applyMemberVOList.add(applyMemberVO);
        }
        return ResultUtils.success(applyMemberVOList);
    }

    // Page<ApplyMember> page = applyMemberService.page(getPage(), new QueryWrapper<ApplyMember>().like(StrUtil.isNotBlank(name),"apply_member_name",name));

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单条数据")
    @GetMapping("/searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:apply:sigln')")
    public BaseResponse<ApplyMemberVO> queryById(@PathVariable("id") Long id) {
        ApplyMember applyMemberByapplyMemberId = this.applyMemberService.findApplyMemberByapplyMemberId(id);
        ApplyMemberVO applyMemberVO = ApplyMemberVO.entityToVO(applyMemberByapplyMemberId);
        return ResultUtils.success(applyMemberVO);
    }

    /**
     * 新增数据
     *
     * @param applyMemberDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping("/save")
    public BaseResponse<Integer> add(@RequestBody ApplyMemberDto applyMemberDto){
        System.out.println("========>"+applyMemberDto.toString());
        if(StringUtils.isAllBlank(applyMemberDto.getName(),applyMemberDto.getEmail(), applyMemberDto.getAddress(),applyMemberDto.getIntroduction())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        ApplyMember applyMember = applyMemberDto.toEntity();
        applyMember.setCreateTime(new Date());
        applyMember.setUpdateTime(new Date());
        applyMember.setDelectTag(0);
        return ResultUtils.success(this.applyMemberService.addApplyMember(applyMember));
    }

    /**
     * 编辑数据
     *
     * @param applyMemberDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:apply:update')")
    public BaseResponse<Integer> edit(@RequestBody ApplyMemberDto applyMemberDto) {
        ApplyMember applyMember = applyMemberDto.toEntity();
        /**
         * 这里需要有一个用于管理员和超级管理员成功同意的接口就是一个特殊的修改接口
         * 0默认没有通过管理员同意，1正在取得联系审查中，2已经同意该用户可以注册账号了
         * 前端给的逻辑 修改 选择 “状态” 改为 0 | 1 | 2
         */
        applyMember.setUpdateTime(new Date());
        return ResultUtils.success(this.applyMemberService.updateApplyMemberByapplyMemberId(applyMember));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除数据")
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('sys:apply:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        ApplyMember applyMember = applyMemberService.findApplyMemberByapplyMemberId(id);
        applyMember.setDelectTag(1);
        return ResultUtils.success(this.applyMemberService.updateApplyMemberByapplyMemberId(applyMember));
    }




}

