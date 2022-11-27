package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Menu;
import com.zzw.animalserve.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/13__6:26
 */
@Data
public class RoleVO {
//    id: string;
//    name: string;
//    identifier: string;
//    order: number;
//    disabled: boolean;
//    menus: Menu[];
    private Long id;
    private String name;
    private String identifier;
    private Integer order;
    private Boolean disabled;
    private List<Long> menus;

    public static RoleVO toRole(Role role){
        RoleVO toRole = new RoleVO();
        toRole.setId(role.getId());
        toRole.setName(role.getName());
        toRole.setIdentifier(role.getCode());
        toRole.setMenus(role.getMenuIds());
        return toRole;
    }


}
