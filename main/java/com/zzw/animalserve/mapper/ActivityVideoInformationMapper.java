package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.ActivityVideoInformation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ActivityVideoInformationMapper  extends BaseMapper<ActivityVideoInformation> {

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