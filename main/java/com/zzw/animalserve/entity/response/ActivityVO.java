package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Activity;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/24__1:05
 */
@Data
public class ActivityVO {

    private Long id;

    private String title;

    private String cover;

    private String introduction;

    private String content;

    private String address;

    private String start;

    private String end;

    private String createdAt;

    private String updatedAt;

    private Integer delete;

    //创建人的姓名
    private String username;

    public static ActivityVO entityToVO(Activity activity) {
        ActivityVO activityVO = new ActivityVO();
        activityVO.setId(activity.getActivityId());
        activityVO.setTitle(activity.getActivityTitle());
        activityVO.setCover(activity.getActivityImage());
        activityVO.setIntroduction(activity.getActivityIntroduction());
        activityVO.setContent(activity.getActivityMainContent());
        activityVO.setAddress(activity.getActivityAddress());
        activityVO.setStart(TimeFormat.formatTime(activity.getStartTime()));
        activityVO.setEnd(TimeFormat.formatTime(activity.getEndTime()));
        activityVO.setCreatedAt(TimeFormat.formatTime(activity.getCreateTime()));
        activityVO.setUpdatedAt(TimeFormat.formatTime(activity.getCreateTime()));
        activityVO.setDelete(activity.getDelectTag());
        activityVO.setUsername(activity.getMember().getMemberName());
        return activityVO;
    }
}
