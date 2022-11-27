package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.ApplyMember;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/18__16:19
 */
@ApiModel(value = "申请会员")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyMemberDto {
    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "申请人姓名")
    private String name;

    @ApiModelProperty(value= "申请人邮箱")
    private String email;

    @ApiModelProperty(value= "申请人地址")
    private String address;

    @ApiModelProperty(value= "申请人电话")
    private String phone;

    @ApiModelProperty(value= "状态")
    private Integer status;

    @ApiModelProperty(value= "申请人的自我介绍")
    private String introduction;

    @ApiModelProperty(value= "用户id")
    private Long uid;

    @ApiModelProperty(value= "delete")
    private Integer delete;

    public ApplyMember toEntity() {
        ApplyMember toEntity = new ApplyMember();
        toEntity.setApplyMemberId(id);
        toEntity.setApplyMemberName(name);
        toEntity.setApplyMemberAddress(address);
        toEntity.setApplyMemberPhone(phone);
        toEntity.setApplyMemberStatus(status);
        toEntity.setApplyMemberEmail(email);
        toEntity.setApplyMemberIntroduction(introduction);
        toEntity.setUpdateId(uid);
        toEntity.setDelectTag(delete);
        return toEntity;
    }
}
