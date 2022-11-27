package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.ActivityVideoInformation;
import com.zzw.animalserve.entity.VideoInformation;
import com.zzw.animalserve.mapper.ActivityVideoInformationMapper;
import com.zzw.animalserve.mapper.VideoInformationMapper;
import com.zzw.animalserve.service.ActivityVideoInformationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class ActivityVideoInformationServiceImpl extends ServiceImpl<ActivityVideoInformationMapper, ActivityVideoInformation> implements ActivityVideoInformationService {

    @Autowired
    private ActivityVideoInformationMapper activityVideoInformationMapper;

    /**
     * 查询表activity_video_information所有信息
     */
    @Override
    public List<ActivityVideoInformation> findAllActivityVideoInformation() {
        return activityVideoInformationMapper.findAllActivityVideoInformation();
    }

    /**
     * 根据主键videoInformationId查询表activity_video_information信息
     *
     * @param videoInformationId
     */
    @Override
    public ActivityVideoInformation findActivityVideoInformationByvideoInformationId(@Param("videoInformationId") Long videoInformationId) {
        return activityVideoInformationMapper.findActivityVideoInformationByvideoInformationId(videoInformationId);
    }

    /**
     * 根据条件查询表activity_video_information信息
     *
     * @param activityVideoInformation
     */
    @Override
    public List<ActivityVideoInformation> findActivityVideoInformationByCondition(ActivityVideoInformation activityVideoInformation) {
        return activityVideoInformationMapper.findActivityVideoInformationByCondition(activityVideoInformation);
    }

    /**
     * 根据主键videoInformationId查询表activity_video_information信息
     *
     * @param videoInformationId
     */
    @Override
    public Integer deleteActivityVideoInformationByvideoInformationId(@Param("videoInformationId") Long videoInformationId) {
        return activityVideoInformationMapper.deleteActivityVideoInformationByvideoInformationId(videoInformationId);
    }

    /**
     * 根据主键videoInformationId更新表activity_video_information信息
     *
     * @param activityVideoInformation
     */
    @Override
    public Integer updateActivityVideoInformationByvideoInformationId(ActivityVideoInformation activityVideoInformation) {
        return activityVideoInformationMapper.updateActivityVideoInformationByvideoInformationId(activityVideoInformation);
    }

    /**
     * 新增表activity_video_information信息
     *
     * @param activityVideoInformation
     */
    @Override
    public Integer addActivityVideoInformation(ActivityVideoInformation activityVideoInformation) {
        return activityVideoInformationMapper.addActivityVideoInformation(activityVideoInformation);
    }

}