package com.zzw.animalserve.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @description(用户请求DTO)
 * @autor: zhouzhengwei
 * @date: 2022/10/10__2:50
 */
@ApiModel(value = "授权用户信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthMemberDto {


    @ApiModelProperty(value= "用户名")
    private String username;

    @ApiModelProperty(value= "密码")
    private String password;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value= "确认密码")
    private String newPassword;

    @ApiModelProperty(value = "临时登录凭证")
    private String code;

    @ApiModelProperty(value= "邮箱")
    private String email;
}
