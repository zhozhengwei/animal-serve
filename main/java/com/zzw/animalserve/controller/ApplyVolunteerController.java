package com.zzw.animalserve.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.ApplyVolunteer;
import com.zzw.animalserve.entity.dto.ApplyVolunteerDto;
import com.zzw.animalserve.entity.response.ApplyVolunteerVO;
import com.zzw.animalserve.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.ApplyVolunteerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "申请志愿者")
@RestController
@RequestMapping("/applyVolunteer")
public class ApplyVolunteerController extends BaseController {

    @Autowired
    private ApplyVolunteerService applyVolunteerService;


    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("申请列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:volunteer:lists')")
    public BaseResponse<List<ApplyVolunteerVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            List<ApplyVolunteer> allApplyVolunteer = applyVolunteerService.findAllApplyVolunteer();
            List<ApplyVolunteerVO> applyVolunteerVOList = new ArrayList<>();
            for (ApplyVolunteer temp:allApplyVolunteer) {
                if(temp.getDelectTag() == 0){
                    ApplyVolunteerVO applyVolunteerVO = ApplyVolunteerVO.entityToVO(temp);
                    applyVolunteerVOList.add(applyVolunteerVO);
                }
            }
            return ResultUtils.success(applyVolunteerVOList);
        }else{
            ApplyVolunteer applyVolunteer = new ApplyVolunteer();
            applyVolunteer.setApplyVolunteerName(name);
            List<ApplyVolunteer> applyVolunteerByCondition = applyVolunteerService.findApplyVolunteerByCondition(applyVolunteer);
            List<ApplyVolunteerVO> applyVolunteerVOList = new ArrayList<>();
            for (ApplyVolunteer temp:applyVolunteerByCondition) {
                if(temp.getDelectTag() == 0){
                    ApplyVolunteerVO applyVolunteerVO = ApplyVolunteerVO.entityToVO(temp);
                    applyVolunteerVOList.add(applyVolunteerVO);
                }
            }
            return ResultUtils.success(applyVolunteerVOList);
        }

    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
//    @PreAuthorize("hasAuthority('sys:volunteer:lists')")
    public BaseResponse<List<ApplyVolunteerVO>> queryBySearch(@RequestBody ApplyVolunteerDto applyMemberDto) {
        ApplyVolunteer applyVolunteer = applyMemberDto.toEntity();
        List<ApplyVolunteer> applyVolunteerByCondition = applyVolunteerService.findApplyVolunteerByCondition(applyVolunteer);
        List<ApplyVolunteerVO> applyVolunteerVOList = new ArrayList<>();
        for (ApplyVolunteer temp:applyVolunteerByCondition) {
            ApplyVolunteerVO applyVolunteerVO = ApplyVolunteerVO.entityToVO(temp);
            applyVolunteerVOList.add(applyVolunteerVO);
        }
        return ResultUtils.success(applyVolunteerVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单条数据")
    @GetMapping("/searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:volunteer:sigln')")
    public BaseResponse<ApplyVolunteerVO> queryById(@PathVariable("id") Long id) {
        ApplyVolunteer applyVolunteerByapplyVolunteerId = this.applyVolunteerService.findApplyVolunteerByapplyVolunteerId(id);
        ApplyVolunteerVO applyVolunteerVO = ApplyVolunteerVO.entityToVO(applyVolunteerByapplyVolunteerId);
        return ResultUtils.success(applyVolunteerVO);
    }

    /**
     * 新增数据
     *
     * @param applyMemberDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping("/save")
    public BaseResponse<Integer> add(@RequestBody ApplyVolunteerDto applyMemberDto) {
        ApplyVolunteer applyVolunteer = applyMemberDto.toEntity();
        applyVolunteer.setCreateTime(new Date());
        applyVolunteer.setUpdateTime(new Date());
        applyVolunteer.setDelectTag(0);
        return ResultUtils.success(this.applyVolunteerService.addApplyVolunteer(applyVolunteer));
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
    public BaseResponse<Integer> edit(@RequestBody ApplyVolunteerDto applyMemberDto) {
        /**
         * 这里需要有一个用于管理员和超级管理员成功同意的接口就是一个特殊的修改接口
         * 0默认没有通过管理员同意，1正在取得联系审查中，2已经同意该用户可以注册账号了
         * 前端给的逻辑 修改 选择 “状态” 改为 0 | 1 | 2
         */
        ApplyVolunteer applyVolunteer = applyMemberDto.toEntity();
        applyVolunteer.setUpdateTime(new Date());
        return ResultUtils.success(this.applyVolunteerService.updateApplyVolunteerByapplyVolunteerId(applyVolunteer));
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
        ApplyVolunteer applyMember = applyVolunteerService.findApplyVolunteerByapplyVolunteerId(id);
        applyMember.setDelectTag(1);
        return ResultUtils.success(this.applyVolunteerService.updateApplyVolunteerByapplyVolunteerId(applyMember));
    }


}