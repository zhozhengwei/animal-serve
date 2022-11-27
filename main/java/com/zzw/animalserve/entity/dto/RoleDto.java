package com.zzw.animalserve.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/15__13:10
 */
@ApiModel(value = "修改角色DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    @ApiModelProperty(value= "角色ID")
    private Long id;

    @ApiModelProperty(value= "角色名称")
    private String name;

    @ApiModelProperty(value= "角色身份")
    private String identifier;

    @ApiModelProperty(value= "排序")
    private Integer order;

    @ApiModelProperty(value= "不知道")
    private Boolean disabled;

    @ApiModelProperty(value= "菜单列表")
    private List<Long> menus;
}
