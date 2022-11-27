package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/12__23:38
 */
@Data
public class MenuVO {
    private long mid ;

    private Long father;

    private String name ;

    private String icon ;

    private String path ;

    private String empower;

    private String part;

    private Integer type;

    private int ordernum ;

    private Integer status;

    private boolean hide ;

    private List<Menu> children;

    public static MenuVO entityMenu(Menu menu) {
        MenuVO vo = new MenuVO();
        vo.setMid(menu.getId());
        vo.setFather(menu.getParentId());
        vo.setName(menu.getName());
        vo.setIcon(menu.getIcon());
        vo.setPath(menu.getPath());
        vo.setEmpower(menu.getPerms());
        vo.setPart(menu.getComponent());
        vo.setType(menu.getType());
        vo.setOrdernum(menu.getOrdernum());
        vo.setStatus(menu.getStatu());
        vo.setChildren(menu.getChildren());
        return vo;
    }
}
