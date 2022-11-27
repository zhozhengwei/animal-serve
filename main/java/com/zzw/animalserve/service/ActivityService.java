package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Activity;
import com.zzw.animalserve.entity.dto.ActivityDto;
import com.zzw.animalserve.entity.dto.ArticleDto;
import com.zzw.animalserve.entity.response.ActivityVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityService extends IService<Activity> {


    PageInfo<ActivityVO> findPage(int pageNum, ActivityDto activityDto);

    PageInfo<ActivityVO> findPageList(int pageNum);

    PageInfo<ActivityVO> history(int pageNum);

    /**
     * 查询表activity所有信息
     */
    List<Activity> findAllActivity();

    /**
     * 根据主键activityId查询表activity信息
     *
     * @param activityId
     */
    Activity findActivityByactivityId(@Param("activityId") Long activityId);

    /**
     * 根据条件查询表activity信息
     *
     * @param activity
     */
    List<Activity> findActivityByCondition(Activity activity);

    /**
     * 根据主键activityId查询表activity信息
     *
     * @param activityId
     */
    Integer deleteActivityByactivityId(@Param("activityId") Long activityId);

    /**
     * 根据主键activityId更新表activity信息
     *
     * @param activity
     */
    Integer updateActivityByactivityId(Activity activity);

    /**
     * 新增表activity信息
     *
     * @param activity
     */
    Integer addActivity(Activity activity);
}