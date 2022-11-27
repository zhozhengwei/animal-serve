package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/14__22:02
 */

@ApiModel(value = "修改用户信息DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    @ApiModelProperty(value= "用户ID")
    private Long id;

    @ApiModelProperty(value= "邮箱")
    private String email;

    @ApiModelProperty(value= "用户名")
    private String username ;

    @ApiModelProperty(value= "电话")
    private String mobile;

    @ApiModelProperty(value= "性别")
    private String gender;

    @ApiModelProperty(value= "头像")
    private String avatar ;

    @ApiModelProperty(value = "临时登录凭证")
    private String code;
    public Member toEntity() {
        Member member = new Member();
        member.setMemberId(id);
        member.setMemberSex(gender);
        member.setMemberName(username);
        member.setMemberEmail(email);
        member.setMemberPhone(mobile);
        member.setMemberImage(avatar);
        return member;
    }
}
