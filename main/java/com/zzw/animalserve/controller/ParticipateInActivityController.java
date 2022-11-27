package com.zzw.animalserve.controller;


import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.ParticipateInActivity;
import com.zzw.animalserve.entity.dto.ParticipateInActivityDto;
import com.zzw.animalserve.entity.response.ParticipateInActivityVO;
import com.zzw.animalserve.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.ParticipateInActivityService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "参加活动信息")
@RestController
@RequestMapping("/participateInActivity")
public class ParticipateInActivityController  extends BaseController {

    @Autowired
    private ParticipateInActivityService participateInActivityService;

    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("活动报名列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:activity:lists')")
    public BaseResponse<List<ParticipateInActivityVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            List<ParticipateInActivity> allParticipateInActivity = participateInActivityService.findAllParticipateInActivity();
            List<ParticipateInActivityVO> participateInActivityVOList = new ArrayList<>();
            for (ParticipateInActivity temp: allParticipateInActivity) {
                if(temp.getDelectTag() == 0){
                    ParticipateInActivityVO participateInActivityVO = ParticipateInActivityVO.entityToVO(temp);
                    participateInActivityVOList.add(participateInActivityVO);
                }
            }
            return ResultUtils.success(participateInActivityVOList);
        }else{
            ParticipateInActivity participateInActivity = new ParticipateInActivity();
            participateInActivity.setParticipateInActivityName(name);
            List<ParticipateInActivity> participateInActivityByCondition = participateInActivityService.findParticipateInActivityByCondition(participateInActivity);
            List<ParticipateInActivityVO> participateInActivityVOList = new ArrayList<>();
            for (ParticipateInActivity temp: participateInActivityByCondition) {
                if(temp.getDelectTag() == 0){
                    ParticipateInActivityVO participateInActivityVO = ParticipateInActivityVO.entityToVO(temp);
                    participateInActivityVOList.add(participateInActivityVO);
                }
            }
            return ResultUtils.success(participateInActivityVOList);
        }
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单个活动信息")
    @GetMapping("/searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:activity:sigln')")
    public BaseResponse<ParticipateInActivityVO> queryById(@PathVariable("id") Long id) {
        ParticipateInActivity participateInActivityByparticipateInActivityId = this.participateInActivityService.findParticipateInActivityByparticipateInActivityId(id);
        ParticipateInActivityVO participateInActivityVO = ParticipateInActivityVO.entityToVO(participateInActivityByparticipateInActivityId);
        return ResultUtils.success(participateInActivityVO);
    }

    @ApiOperation("个人参加活动信息")
    @GetMapping("/searchMemberList/{id}")
    public BaseResponse<List<ParticipateInActivityVO>> queryByAId(@PathVariable("id") Long id) {
        ParticipateInActivity participateInActivity = new ParticipateInActivity();
        participateInActivity.setCreateId(id);
        List<ParticipateInActivity> participateInActivityByCondition = participateInActivityService.findParticipateInActivityByCondition(participateInActivity);
        List<ParticipateInActivityVO> participateInActivityVOList = new ArrayList<>();
        for (ParticipateInActivity temp: participateInActivityByCondition) {
            if(temp.getDelectTag() == 0){
                ParticipateInActivityVO participateInActivityVO = ParticipateInActivityVO.entityToVO(temp);
                participateInActivityVOList.add(participateInActivityVO);
            }
        }
        return ResultUtils.success(participateInActivityVOList);
    }

    @ApiOperation("条件查询")
    @PostMapping("/searchList")
    public BaseResponse<List<ParticipateInActivityVO>> queryBySearcherIf(@RequestBody ParticipateInActivityDto participateInActivityDto) {
        ParticipateInActivity participateInActivity = participateInActivityDto.toEntity();
        List<ParticipateInActivity> participateInActivityByCondition = participateInActivityService.findParticipateInActivityByCondition(participateInActivity);
        List<ParticipateInActivityVO> participateInActivityVOList = new ArrayList<>();
        for (ParticipateInActivity temp: participateInActivityByCondition) {
            if(temp.getDelectTag() == 0){
                ParticipateInActivityVO participateInActivityVO = ParticipateInActivityVO.entityToVO(temp);
                participateInActivityVOList.add(participateInActivityVO);
            }
        }
        return ResultUtils.success(participateInActivityVOList);
    }

    /**
     * 新增数据
     *
     * @param participateInActivityDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增活动参加")
    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('sys:activity:save')")
    public BaseResponse<Integer> add(@RequestBody ParticipateInActivityDto participateInActivityDto) {
        ParticipateInActivity participateInActivity = participateInActivityDto.toEntity();
        participateInActivity.setCreateTime(new Date());
        participateInActivity.setUpdateTime(new Date());
        participateInActivity.setParticipateInActivityStatus(0);
        participateInActivity.setDelectTag(0);
        if(!participateInActivity.getParticipateInActivityEmail().equals(participateInActivityDto.getNewEmail())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"输入的两次邮箱不一致");
        }
        if(participateInActivityDto.getPhone() == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"输入的手机号不能为空");
        }
        Integer integer = this.participateInActivityService.addParticipateInActivity(participateInActivity);
        return ResultUtils.success(integer);
    }

    /**
     * 编辑数据
     *
     * @param participateInActivityDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑信息")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:activity:update')")
    public BaseResponse<Integer> edit(@RequestBody ParticipateInActivityDto participateInActivityDto) {
        /**
         * 这里需要有一个用于管理员和超级管理员成功同意的接口就是一个特殊的修改接口
         * 0默认没有通过管理员同意，1正在取得联系审查中，2已经同意该用户可以注册账号了
         * 前端给的逻辑 修改 选择 “状态” 改为 0 | 1 | 2
         */
        ParticipateInActivity participateInActivity = participateInActivityDto.toEntity();
        participateInActivity.setUpdateTime(new Date());
        return ResultUtils.success(this.participateInActivityService.updateParticipateInActivityByparticipateInActivityId(participateInActivity));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除信息")
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('sys:activity:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        ParticipateInActivity activity = participateInActivityService.findParticipateInActivityByparticipateInActivityId(id);
        activity.setDelectTag(1);
        return ResultUtils.success(this.participateInActivityService.updateParticipateInActivityByparticipateInActivityId(activity));
    }


}