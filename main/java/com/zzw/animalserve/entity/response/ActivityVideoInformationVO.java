package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.ActivityVideoInformation;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__1:50
 */
@Data
public class ActivityVideoInformationVO {

    private Long vid;

    private Long aid;

    private String url;

    private String content;

    private String poster;

    private String title;

    private String start;

    private String end;

    public static ActivityVideoInformationVO entityToVO(ActivityVideoInformation activityVideoInformation){
        ActivityVideoInformationVO activityVideoInformationVO = new ActivityVideoInformationVO();
        activityVideoInformationVO.setAid(activityVideoInformation.getActivityId());
        activityVideoInformationVO.setVid(activityVideoInformation.getVideoInformationId());
        activityVideoInformationVO.setTitle(activityVideoInformation.getActivity().getActivityTitle());
        activityVideoInformationVO.setUrl(activityVideoInformation.getVideoInformation().getVideoInformationUrl());
        activityVideoInformationVO.setContent(activityVideoInformation.getVideoInformation().getVideoInformationContent());
        activityVideoInformationVO.setPoster(activityVideoInformation.getActivity().getActivityImage());
        activityVideoInformationVO.setStart(TimeFormat.formatTime(activityVideoInformation.getActivity().getStartTime()));
        activityVideoInformationVO.setEnd(TimeFormat.formatTime(activityVideoInformation.getActivity().getEndTime()));
        return activityVideoInformationVO;
    }
}
