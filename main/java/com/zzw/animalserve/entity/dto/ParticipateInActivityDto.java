package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.ParticipateInActivity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/25__18:44
 */

@ApiModel(value = "参加活动DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipateInActivityDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "活动ID")
    private Long acid;

    @ApiModelProperty(value= "参加者姓名")
    private String name;

    @ApiModelProperty(value= "参加者邮箱")
    private String email;

    @ApiModelProperty(value= "检查两次输入的邮箱")
    private String newEmail;

    @ApiModelProperty(value= "手机号")
    private String phone;

    @ApiModelProperty(value= "状态")
    private Integer status;

    @ApiModelProperty(value= "用户信息")
    private Long uid;

    public ParticipateInActivity toEntity() {
        ParticipateInActivity participateInActivity = new ParticipateInActivity();
        participateInActivity.setParticipateInActivityId(id);
        participateInActivity.setActivityId(acid);
        participateInActivity.setParticipateInActivityName(name);
        participateInActivity.setParticipateInActivityEmail(email);
        participateInActivity.setCreateId(uid);
        participateInActivity.setParticipateInActivityStatus(status);
        participateInActivity.setUpdateId(uid);
        return participateInActivity;
    }
}
