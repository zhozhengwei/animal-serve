package com.zzw.animalserve.entity.dto;


import com.zzw.animalserve.entity.ActivityVideoInformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/3__16:42
 */

@ApiModel(value = "视频活动DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityVideoInformationDto {

    @ApiModelProperty(value= "活动ID")
    private Long aid;

    @ApiModelProperty(value= "视频ID")
    private Long vid;

    @ApiModelProperty(value= "查询根据")
    private String text;

    public ActivityVideoInformation toEntity() {
        ActivityVideoInformation activityVideoInformation = new ActivityVideoInformation();
        activityVideoInformation.setActivityId(aid);
        activityVideoInformation.setVideoInformationId(vid);
        return activityVideoInformation;
    }


}
