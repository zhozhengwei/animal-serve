package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzw.animalserve.entity.ActivityVideoInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityVideoInformationService  extends IService<ActivityVideoInformation> {

    /**
     * 查询表activity_video_information所有信息
     */
    List<ActivityVideoInformation> findAllActivityVideoInformation();

    /**
     * 根据主键videoInformationId查询表activity_video_information信息
     *
     * @param videoInformationId
     */
    ActivityVideoInformation findActivityVideoInformationByvideoInformationId(@Param("videoInformationId") Long videoInformationId);

    /**
     * 根据条件查询表activity_video_information信息
     *
     * @param activityVideoInformation
     */
    List<ActivityVideoInformation> findActivityVideoInformationByCondition(ActivityVideoInformation activityVideoInformation);

    /**
     * 根据主键videoInformationId查询表activity_video_information信息
     *
     * @param videoInformationId
     */
    Integer deleteActivityVideoInformationByvideoInformationId(@Param("videoInformationId") Long videoInformationId);

    /**
     * 根据主键videoInformationId更新表activity_video_information信息
     *
     * @param activityVideoInformation
     */
    Integer updateActivityVideoInformationByvideoInformationId(ActivityVideoInformation activityVideoInformation);

    /**
     * 新增表activity_video_information信息
     *
     * @param activityVideoInformation
     */
    Integer addActivityVideoInformation(ActivityVideoInformation activityVideoInformation);
}