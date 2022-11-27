package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Activity;
import com.zzw.animalserve.entity.ActivityVideoInformation;
import com.zzw.animalserve.entity.ParticipateInActivity;
import com.zzw.animalserve.entity.VideoInformation;
import com.zzw.animalserve.entity.dto.ActivityDto;
import com.zzw.animalserve.entity.response.ActivityVO;
import com.zzw.animalserve.service.ActivityVideoInformationService;
import com.zzw.animalserve.service.ParticipateInActivityService;
import com.zzw.animalserve.service.VideoInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.ActivityService;

import java.util.*;


@Api(tags = "活动信息")
@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityVideoInformationService activityVideoInformationService;

    @Autowired
    private VideoInformationService videoInformationService;


    @Autowired
    private ParticipateInActivityService participateInActivityService;

    /**
     * 分页查询
     *
     * @param activityDto
     * @return
     */
    @ApiOperation("前台查询列表")
    @PostMapping("/list")
//    @PreAuthorize("hasAuthority('sys:activity:lists')")
    public BaseResponse<PageInfo<ActivityVO>> queryByPage(@RequestBody ActivityDto activityDto, Integer pageNum) {
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<ActivityVO> page = activityService.findPage(pageNum, activityDto);
        return ResultUtils.success(page);
    }

    @ApiOperation("前台活动查询列表")
    @GetMapping("/listAll/{pageNum}")
//    @PreAuthorize("hasAuthority('sys:activity:lists')")
    public BaseResponse<PageInfo<ActivityVO>> queryByPageList(@PathVariable("pageNum") Integer pageNum) {
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<ActivityVO> page = activityService.findPageList(pageNum);
        return ResultUtils.success(page);
    }




    @ApiOperation("前台历史活动列表")
    @GetMapping("/history/{pageNum}")
//    @PreAuthorize("hasAuthority('sys:activity:lists')")
    public BaseResponse<PageInfo<ActivityVO>> queryByPageHistory(@PathVariable("pageNum") Integer pageNum) {
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<ActivityVO> page = activityService.history(pageNum);
        return ResultUtils.success(page);
    }

    @ApiOperation("全部活动列表")
    @GetMapping("/allList")
    public BaseResponse<List<ActivityVO>> listAll(){
        if(redisCache.getCacheList("activity").size() == 0){
            List<Activity> allActivity = activityService.findAllActivity();
            List<ActivityVO> activityVOList = new ArrayList<>();
            for (Activity temp : allActivity) {
                if(temp.getDelectTag() == 0){
                    ActivityVO activityVO = ActivityVO.entityToVO(temp);
                    activityVOList.add(activityVO);
                }
            }
            redisCache.setCacheList("activity",activityVOList);
        }
        List<ActivityVO> temp = redisCache.getCacheList("activity");
        return ResultUtils.success(temp);
    }

    @ApiOperation("条件查询列表")
    @PostMapping("/listSearch")
    public BaseResponse<List<ActivityVO>> listSearch(@RequestBody ActivityDto activityDto){
        Activity activity = activityDto.toEntity();
        List<Activity> allActivity = activityService.findActivityByCondition(activity);
        List<ActivityVO> activityVOList = new ArrayList<>();
        for (Activity temp : allActivity) {
            if(temp.getDelectTag() == 0){
                ActivityVO activityVO = ActivityVO.entityToVO(temp);
                activityVOList.add(activityVO);
            }
        }
        return ResultUtils.success(activityVOList);
    }


    @ApiOperation("前台前三参加人数的列表")
    @GetMapping("/listPerson")
    public BaseResponse<List<ActivityVO>> listPersonCount(){
        List<Activity> allActivity = activityService.findAllActivity();
        Map<Long,Integer> map = new TreeMap<Long, Integer>();
        for (Activity temp: allActivity) {
            ParticipateInActivity participateInActivity = new ParticipateInActivity();
            participateInActivity.setActivityId(temp.getActivityId());
            List<ParticipateInActivity> participateInActivityByCondition = participateInActivityService.findParticipateInActivityByCondition(participateInActivity);
            //存放所有活动ID对应的报名数量
            map.put(temp.getActivityId(),participateInActivityByCondition.size());
        }

        // 降序比较器
        Comparator<Map.Entry<Long,Integer>> valueComparator = new Comparator<Map.Entry<Long,Integer>>() {
            @Override
            public int compare(Map.Entry<Long, Integer> o1, Map.Entry<Long, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        };

        // map转换成list进行排序
        List<Map.Entry<Long,Integer>> list = new ArrayList<Map.Entry<Long,Integer>>(map.entrySet());
        // 排序
        Collections.sort(list,valueComparator);
        List<Activity> activityList = new ArrayList<>();
        for (Map.Entry<Long,Integer> entry : map.entrySet()) {
            Long key = entry.getKey();
            Activity activityByactivityId = activityService.findActivityByactivityId(key);
            activityList.add(activityByactivityId);
        }
        List<Activity> activityList1 = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Activity activity = activityList.get(i);
            activityList1.add(activity);
        }
        List<ActivityVO> activityVOList = new ArrayList<>();
        for (Activity temp : activityList1) {
            if(temp.getDelectTag() == 0){
                ActivityVO activityVO = ActivityVO.entityToVO(temp);
                activityVOList.add(activityVO);
            }
        }
        return ResultUtils.success(activityVOList);
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
    public BaseResponse<ActivityVO> queryById(@PathVariable("id") Long id) {
        Activity activityByactivityId = this.activityService.findActivityByactivityId(id);
        ActivityVO activityVO = ActivityVO.entityToVO(activityByactivityId);
        return ResultUtils.success(activityVO);
    }

    /**
     * 新增数据
     *
     * @param activityDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增活动")
    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('sys:activity:save')")
    public BaseResponse<Integer> add(@RequestBody ActivityDto activityDto) {
        Activity activity = activityDto.toEntity();
        activity.setCreateTime(new Date());
        activity.setDelectTag(0);
        Integer integer = activityService.addActivity(activity);
//        List<Long> videoInformation = activityDto.getVideoInformation();
//        if(videoInformation.size()>0){
//            ActivityVideoInformation addActivityVideoInformation = new ActivityVideoInformation();
//            for (Long temp : videoInformation) {
//                addActivityVideoInformation.setActivityId(Long.valueOf(integer));
//                addActivityVideoInformation.setVideoInformationId(temp);
//                activityVideoInformationService.addActivityVideoInformation(addActivityVideoInformation);
//            }
//        }
        redisCache.deleteObject("activity");
        return ResultUtils.success(integer);
    }

    /**
     * 编辑数据
     *
     * @param activityDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑活动信息")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:activity:update')")
    public BaseResponse<Integer> edit(@RequestBody ActivityDto activityDto) {
        Activity activity = activityDto.toEntity();
        activity.setCreateTime(new Date());
        redisCache.deleteObject("activity");
        return ResultUtils.success(this.activityService.updateActivityByactivityId(activity));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除活动")
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('sys:activity:delete')")
    @Transactional
    public BaseResponse<Integer> deleteById(Long id) {
        Integer integer1 = activityService.deleteActivityByactivityId(id);
        ActivityVideoInformation activityVideoInformation = new ActivityVideoInformation();
        activityVideoInformation.setActivityId(id);
        List<ActivityVideoInformation> activityVideoInformationByCondition = activityVideoInformationService.findActivityVideoInformationByCondition(activityVideoInformation);
        for (ActivityVideoInformation template: activityVideoInformationByCondition) {
            videoInformationService.deleteVideoInformationByvideoInformationId( template.getVideoInformationId());
            activityVideoInformationService.deleteActivityVideoInformationByvideoInformationId(template.getVideoInformationId());
        }
        redisCache.deleteObject("activity");
        return ResultUtils.success(integer1);
    }


}