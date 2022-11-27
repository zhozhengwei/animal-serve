package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;


@ApiModel(value = "修改菜单DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
//
//    private Long mid ;
//
//    private String identifier;
//
//    private String name ;
//
//    private String icon ;
//
//    private String path ;
//
//    private Long parentId ;
//
//    private boolean hide ;
//
//    @Min(value = 0,message = "排序值不能小于0")
//    private int idx ;
    @ApiModelProperty(value= "菜单ID")
    private Long mid;

    @ApiModelProperty(value= "父菜单ID，一级菜单为0")
    private Long father;

    @ApiModelProperty(value= "菜单名称")
    private String name;

    @ApiModelProperty(value= "前端图标")
    private String icon;

    @ApiModelProperty(value= "路由")
    private String path;

    @ApiModelProperty(value= "权限标识")
    private String empower;

    @ApiModelProperty(value= "前端路由")
    private String part;

    @ApiModelProperty(value= "几级目录")
    private Integer type;

    @ApiModelProperty(value= "排序")
    private int ordernum ;

    @ApiModelProperty(value= "是否存在")
    private Integer status;

    @ApiModelProperty(value= "不知道")
    private boolean hide ;

    private List<MenuDto> children = new ArrayList<>();

    public Menu toEntity() {
        Menu menu = new Menu();
        menu.setId(mid);
        menu.setParentId(father);
        menu.setName(name);
        menu.setIcon(icon);
        menu.setPerms(empower);
        menu.setPath(path);
        menu.setComponent(part);
        menu.setType(type);
        menu.setStatu(status);
        menu.setOrdernum(ordernum);
        return menu;
    }


}
