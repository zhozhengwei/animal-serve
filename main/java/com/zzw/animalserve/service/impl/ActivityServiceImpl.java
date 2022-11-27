package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Activity;
import com.zzw.animalserve.entity.dto.ActivityDto;
import com.zzw.animalserve.entity.response.ActivityVO;
import com.zzw.animalserve.mapper.ActivityMapper;
import com.zzw.animalserve.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.*;

@Slf4j
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public PageInfo<ActivityVO> findPage(int pageNum, ActivityDto activityDto) {
        Activity activity = activityDto.toEntity();
        PageHelper.startPage(pageNum, 10);
        List<Activity> activityByCondition = activityMapper.findActivityByCondition(activity);
        PageInfo pageResult = new PageInfo(activityByCondition);
        Collections.sort(activityByCondition, new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });
        List<ActivityVO> activityVOList = new ArrayList<>();
        for (Activity temp:activityByCondition) {
            if(temp.getDelectTag() == 0){
                ActivityVO vo = ActivityVO.entityToVO(temp);
                activityVOList.add(vo);
            }
        }
        pageResult.setList(activityVOList);
        return pageResult;
    }

    @Override
    public PageInfo<ActivityVO> findPageList(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Activity> allActivity = activityMapper.findAllActivity();
        PageInfo pageResult = new PageInfo(allActivity);
        Collections.sort(allActivity, new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });
        List<ActivityVO> activityVOList = new ArrayList<>();
        for (Activity temp:allActivity) {
            if(temp.getDelectTag() == 0){
                ActivityVO vo = ActivityVO.entityToVO(temp);
                activityVOList.add(vo);
            }
        }
        pageResult.setList(activityVOList);
        return pageResult;
    }


    @Override
    public PageInfo<ActivityVO> history(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Activity> allActivity = activityMapper.findAllActivity();
        PageInfo pageResult = new PageInfo(allActivity);
        List<Activity> activity = new ArrayList<>();
        Date date=new Date();
        for (Activity temp: allActivity) {
            if(date.getTime() > temp.getEndTime().getTime() && temp.getDelectTag() == 0){
                activity.add(temp);
            }
        }
        log.warn("查询一个历史记录=========>"+activity.toString());
        List<ActivityVO> activityVOList = new ArrayList<>();
        for (Activity temp:activity) {
            ActivityVO vo = ActivityVO.entityToVO(temp);
            activityVOList.add(vo);
        }
        pageResult.setList(activityVOList);
        return pageResult;
    }



    /**
     * 查询表activity所有信息
     */
    @Override
    public List<Activity> findAllActivity() {
        return activityMapper.findAllActivity();
    }

    /**
     * 根据主键activityId查询表activity信息
     *
     * @param activityId
     */
    @Override
    public Activity findActivityByactivityId(@Param("activityId") Long activityId) {
        return activityMapper.findActivityByactivityId(activityId);
    }

    /**
     * 根据条件查询表activity信息
     *
     * @param activity
     */
    @Override
    public List<Activity> findActivityByCondition(Activity activity) {
        return activityMapper.findActivityByCondition(activity);
    }

    /**
     * 根据主键activityId查询表activity信息
     *
     * @param activityId
     */
    @Override
    public Integer deleteActivityByactivityId(@Param("activityId") Long activityId) {
        return activityMapper.deleteActivityByactivityId(activityId);
    }

    /**
     * 根据主键activityId更新表activity信息
     *
     * @param activity
     */
    @Override
    public Integer updateActivityByactivityId(Activity activity) {
        return activityMapper.updateActivityByactivityId(activity);
    }

    /**
     * 新增表activity信息
     *
     * @param activity
     */
    @Override
    public Integer addActivity(Activity activity) {
        return activityMapper.addActivity(activity);
    }

}