package com.zzw.animalserve.controller;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Member;
import com.zzw.animalserve.entity.dto.MenuDto;
import com.zzw.animalserve.common.result.Result;
import com.zzw.animalserve.entity.Menu;
import com.zzw.animalserve.entity.RoleMenu;
import com.zzw.animalserve.entity.response.MenuVO;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.service.MemberService;
import com.zzw.animalserve.service.MenuService;
import com.zzw.animalserve.service.RoleMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.zzw.animalserve.common.BaseController;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/menu")
@Api(tags = "Menu")
public class MenuController extends BaseController {

    @Autowired
    RoleMenuService roleMenuService;

    @GetMapping("/nav")
    public BaseResponse<Object> nav(Principal principal) {
        Member user = memberService.getByUsername(principal.getName());

        // 获取权限信息
        String authorityInfo = memberService.getUserAuthorityInfo(user.getMemberId());// ROLE_admin,ROLE_normal,sys:user:list,....
        String[] authorityInfoArray = StringUtils.tokenizeToStringArray(authorityInfo, ",");

        // 获取导航栏信息
        List<MenuDto> navs = menuService.getCurrentUserNav();

        System.out.println("数据:"+navs);

        return ResultUtils.success(MapUtil.builder()
                .put("authoritys", authorityInfoArray)
                .put("nav", navs)
                .map()
        );
    }

    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public BaseResponse<MenuVO> info(@PathVariable(name = "id") Long id) {
        Menu menu = menuService.getById(id);
        MenuVO menuVO = MenuVO.entityMenu(menu);
        return ResultUtils.success(menuVO);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public  BaseResponse<List<MenuVO>> list() {
        List<Menu> menus = menuService.tree();
        List<MenuVO> list = new ArrayList<>();
        for (Menu temp : menus) {
            MenuVO menuVO = MenuVO.entityMenu(temp);
            list.add(menuVO);
        }
        return ResultUtils.success(list);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:menu:save')")
    public BaseResponse<MenuVO> save( @RequestBody MenuDto menuDto) {
        Menu menu = menuDto.toEntity();
        menu.setCreated(LocalDateTime.now());
        menuService.save(menu);
        MenuVO menuVO = MenuVO.entityMenu(menu);
        return ResultUtils.success(menuVO);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:menu:update')")
    public BaseResponse<MenuVO> update( @RequestBody MenuDto menuDto) {
        Menu menu = menuDto.toEntity();
        menu.setUpdated(LocalDateTime.now());
        menuService.updateById(menu);
        MenuVO menuVO = MenuVO.entityMenu(menu);
        // 清除所有与该菜单相关的权限缓存
        memberService.clearUserAuthorityInfoByMenuId(menu.getId());
        return ResultUtils.success(menuVO);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    public BaseResponse<Object> delete(@PathVariable("id") Long id) {

        int count = menuService.count(new QueryWrapper<Menu>().eq("parent_id", id));
        if (count > 0) {
//            return Result.fail("请先删除子菜单");
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请先删除子菜单");
        }

        // 清除所有与该菜单相关的权限缓存
        memberService.clearUserAuthorityInfoByMenuId(id);

        menuService.removeById(id);

        // 同步删除中间关联表
        roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("menu_id", id));
        return ResultUtils.success("");
    }
}
