package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.ApplyVolunteer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/22__23:21
 */
@ApiModel(value = "申请志愿者")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyVolunteerDto {

    @ApiModelProperty(value= "申请人姓名")
    private Long id;

    @ApiModelProperty(value= "申请人姓名")
    private String name;

    @ApiModelProperty(value= "申请人邮箱")
    private String email;

    @ApiModelProperty(value= "申请人地址")
    private String address;

    @ApiModelProperty(value= "申请人电话")
    private String phone;

    @ApiModelProperty(value= "申请人的自我介绍")
    private String introduction;

    @ApiModelProperty(value= "申请人的技能介绍")
    private String skillIntroduction;

    @ApiModelProperty(value= "状态")
    private Integer status;

    @ApiModelProperty(value= "是否为会员")
    private Long uid;

    @ApiModelProperty(value= "delete")
    private Integer delete;

    public ApplyVolunteer toEntity() {
        ApplyVolunteer toEntity = new ApplyVolunteer();
        toEntity.setApplyVolunteerId(id);
        toEntity.setApplyVolunteerName(name);
        toEntity.setApplyVolunteerEmail(email);
        toEntity.setApplyVolunteerAddress(address);
        toEntity.setApplyVolunteerIntroduction(introduction);
        toEntity.setApplyVolunteerSkillIntroduction(skillIntroduction);
        toEntity.setCreateId(uid);
        toEntity.setUpdateId(uid);
        toEntity.setApplyVolunteerStatus(status);
        toEntity.setDelectTag(delete);
        return toEntity;
    }


}
