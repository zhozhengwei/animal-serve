package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Activity;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {

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