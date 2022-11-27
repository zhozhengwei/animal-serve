package com.zzw.animalserve.controller;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.common.lang.Const;
import com.zzw.animalserve.common.result.Result;
import com.zzw.animalserve.entity.Role;
import com.zzw.animalserve.entity.RoleMenu;
import com.zzw.animalserve.entity.UserRole;
import com.zzw.animalserve.entity.response.RoleVO;
import com.zzw.animalserve.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.zzw.animalserve.common.BaseController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('sys:role:list')")
    public BaseResponse<RoleVO> info(@PathVariable Long id){
        Role role = roleService.getById(id);
        List<RoleMenu> roleMenus = roleMenuService.list(new QueryWrapper<RoleMenu>().eq("role_id", id));
        //stream：把一个数据源转化成流
        //map():用于映射每个元素到对应的结果
        List<Long> menuIds = roleMenus.stream().map(p -> p.getMenuId()).collect(Collectors.toList());
        role.setMenuIds(menuIds);
        RoleVO roleVO = RoleVO.toRole(role);
        return ResultUtils.success(roleVO);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:role:list')")
    public BaseResponse<Object> list(String name){
        Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));
        return ResultUtils.success(roles);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:role:save')")
    public BaseResponse<RoleVO> save(@RequestBody Role role){
        role.setCreated(LocalDateTime.now());
        role.setStatu(Const.STATUS_ON);
        roleService.save(role);
        RoleVO roleVO = RoleVO.toRole(role);
        return ResultUtils.success(roleVO);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public BaseResponse<RoleVO> update(@RequestBody Role role){
        role.setUpdated(LocalDateTime.now());
        roleService.updateById(role);
        RoleVO roleVO = RoleVO.toRole(role);
        return ResultUtils.success(roleVO);
    }

    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public BaseResponse<Object> delete(@RequestBody Long[] ids){
        roleService.removeByIds(Arrays.asList(ids));
        //同步删除其它的数据
        roleMenuService.remove(new QueryWrapper<RoleMenu>().in("role_id",ids));
        userRoleService.remove(new QueryWrapper<UserRole>().in("role_id",ids));
        return ResultUtils.success("");
    }

    @Transactional
    @PostMapping("/perm/{roleId}")
    @PreAuthorize("hasAuthority('sys:role:perm')")
    public BaseResponse<Object> perm(@PathVariable Long roleId,@RequestBody Long[] menuIds){
        List<RoleMenu> roleMenus_list = new ArrayList<>();
        Arrays.stream(menuIds).forEach(menuId ->{
            RoleMenu rm = new RoleMenu();
            rm.setMenuId(menuId);
            rm.setRoleId(roleId);
            roleMenus_list.add(rm);
        });

        roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("role_id",roleId));
        roleMenuService.saveBatch(roleMenus_list);
        //清除所有用户的权限缓存信息
        memberService.clearUserAuthorityInfoByRoleId(roleId);
        return ResultUtils.success(menuIds);
    }

}
