package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Activity;
import com.zzw.animalserve.utils.TimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/18__2:40
 */

@ApiModel(value = "活动DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "活动主题")
    private String title;

    @ApiModelProperty(value= "活动封面")
    private String cover;

    @ApiModelProperty(value= "活动简介")
    private String introduction;

    @ApiModelProperty(value= "活动主体说明")
    private String content;

    @ApiModelProperty(value= "活动地址")
    private String address;

    @ApiModelProperty(value= "开始时间")
    private String start;

    @ApiModelProperty(value= "结束时间")
    private String end;

    @ApiModelProperty(value= "创建人")
    private Long uid;

    @ApiModelProperty(value= "视频对照地址")
    private List<Long> videoInformation;

    public Activity toEntity() {
        Activity toEntity = new Activity();
        toEntity.setActivityId(id);
        toEntity.setActivityTitle(title);
        toEntity.setActivityImage(cover);
        toEntity.setActivityIntroduction(introduction);
        toEntity.setActivityMainContent(content);
        toEntity.setActivityAddress(address);
        if(!StringUtils.isAllBlank(start) && !StringUtils.isAllBlank(end)){
            toEntity.setStartTime(TimeFormat.dateTime(start));
            toEntity.setEndTime(TimeFormat.dateTime(end));
        }
        toEntity.setCreateId(uid);
        toEntity.setUpdateId(uid);
        return toEntity;
    }
}
