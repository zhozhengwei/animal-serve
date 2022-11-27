package com.zzw.animalserve.controller;

import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Activity;
import com.zzw.animalserve.entity.ActivityVideoInformation;
import com.zzw.animalserve.entity.Animalia;
import com.zzw.animalserve.entity.VideoInformation;
import com.zzw.animalserve.entity.dto.ActivityDto;
import com.zzw.animalserve.entity.dto.ActivityVideoInformationDto;
import com.zzw.animalserve.entity.dto.AnimaliaDto;
import com.zzw.animalserve.entity.dto.VideoDto;
import com.zzw.animalserve.entity.response.ActivityVideoInformationVO;
import com.zzw.animalserve.entity.response.VideoVO;
import com.zzw.animalserve.service.ActivityService;
import com.zzw.animalserve.service.VideoInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.ActivityVideoInformationService;

import java.util.*;

@Api(tags = "活动视频信息")
@RestController
@RequestMapping("/activityVideoInformation")
public class ActivityVideoInformationController {

    @Autowired
    private ActivityVideoInformationService activityVideoInformationService;

    @Autowired
    private VideoInformationService videoInformationService;

    @Autowired
    private ActivityService activityService;

    /**
     * 分页查询
     *
     * @param
     * @return
     */
    @ApiOperation("活动视频列表")
    @PostMapping("/list")
//    @PreAuthorize("hasAuthority('sys:activity:lists')")
    public BaseResponse<List<ActivityVideoInformationVO>> queryByPage() {
        List<ActivityVideoInformation> allActivityVideoInformation = activityVideoInformationService.findAllActivityVideoInformation();
        Collections.sort(allActivityVideoInformation, new Comparator<ActivityVideoInformation>() {

            @Override
            public int compare(ActivityVideoInformation o1, ActivityVideoInformation o2) {
                return o2.getActivity().getEndTime().compareTo(o1.getActivity().getEndTime());
            }
        });
        List<ActivityVideoInformationVO> activityVideoInformationVOList = new ArrayList<>();
        for (ActivityVideoInformation temp: allActivityVideoInformation) {
            if(temp.getActivity().getDelectTag() == 0){
                ActivityVideoInformationVO activityVideoInformationVO = ActivityVideoInformationVO.entityToVO(temp);
                activityVideoInformationVOList.add(activityVideoInformationVO);
            }
        }
        return ResultUtils.success(activityVideoInformationVOList);
    }

    @ApiOperation("活动条件查询列表")
    @PostMapping("/listActivity")
//    @PreAuthorize("hasAuthority('sys:activity:lists')")
    public BaseResponse<List<ActivityVideoInformationVO>> queryBySearcherIf(@RequestBody ActivityVideoInformationDto activityVideoInformationDto) {
        Activity activity = new Activity();
        activity.setActivityTitle(activityVideoInformationDto.getText());
        List<Activity> activityByCondition = activityService.findActivityByCondition(activity);
        List<ActivityVideoInformation> activityVideoInformations = new ArrayList<>();
        for (Activity temp: activityByCondition) {
            ActivityVideoInformation activityVideoInformation = new ActivityVideoInformation();
            activityVideoInformation.setActivityId(temp.getActivityId());
            List<ActivityVideoInformation> activityVideoInformationByCondition = activityVideoInformationService.findActivityVideoInformationByCondition(activityVideoInformation);
            for (ActivityVideoInformation temp1: activityVideoInformationByCondition) {
                activityVideoInformations.add(temp1);
            }
        }
        List<ActivityVideoInformationVO> activityVideoInformationVOList = new ArrayList<>();
        for (ActivityVideoInformation temp: activityVideoInformations) {
            if(temp.getActivity().getDelectTag() == 0){
                ActivityVideoInformationVO activityVideoInformationVO = ActivityVideoInformationVO.entityToVO(temp);
                activityVideoInformationVOList.add(activityVideoInformationVO);
            }
        }
        return ResultUtils.success(activityVideoInformationVOList);
    }

    @ApiOperation("单个活动视频列表")
    @GetMapping("/searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:book:sigln')")
    public BaseResponse<List<ActivityVideoInformationVO>> queryById(@PathVariable("id") Long id) {
        ActivityVideoInformation activityVideoInformation = new ActivityVideoInformation();
        activityVideoInformation.setActivityId(id);
        List<ActivityVideoInformation> activityVideoInformationByCondition = activityVideoInformationService.findActivityVideoInformationByCondition(activityVideoInformation);
        List<ActivityVideoInformationVO> activityVideoInformationVOList = new ArrayList<>();
        for (ActivityVideoInformation temp: activityVideoInformationByCondition) {
            ActivityVideoInformationVO activityVideoInformationVO = ActivityVideoInformationVO.entityToVO(temp);
            activityVideoInformationVOList.add(activityVideoInformationVO);
        }
        return ResultUtils.success(activityVideoInformationVOList);
    }

    @ApiOperation("编辑活动信息")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:activity:update')")
    public BaseResponse<Integer> edit(@RequestBody ActivityVideoInformationDto activityVideoInformationDto) {
        ActivityVideoInformation activityVideoInformation = activityVideoInformationDto.toEntity();
        return ResultUtils.success(this.activityVideoInformationService.updateActivityVideoInformationByvideoInformationId(activityVideoInformation));
    }

    @ApiOperation("新增信息")
    @PostMapping("/save")
    //@PreAuthorize("hasAuthority('sys:animalia:save')")
    public BaseResponse<Integer> add(@RequestBody ActivityVideoInformationDto activityVideoInformationDto) {
        ActivityVideoInformation activityVideoInformation = activityVideoInformationDto.toEntity();
        Integer integer = activityVideoInformationService.addActivityVideoInformation(activityVideoInformation);
        return ResultUtils.success(integer);
    }



    @ApiOperation("删除信息")
    @DeleteMapping("/delete")
    //@PreAuthorize("hasAuthority('sys:animalia:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        return ResultUtils.success(this.activityVideoInformationService.deleteActivityVideoInformationByvideoInformationId(id));
    }


}