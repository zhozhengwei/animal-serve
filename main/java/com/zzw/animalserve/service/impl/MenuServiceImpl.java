package com.zzw.animalserve.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzw.animalserve.entity.Member;
import com.zzw.animalserve.entity.dto.MenuDto;
import com.zzw.animalserve.entity.Menu;
import com.zzw.animalserve.mapper.MemberMapper;
import com.zzw.animalserve.mapper.MenuMapper;
import com.zzw.animalserve.service.MemberService;
import com.zzw.animalserve.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Byterain
 * @since 2022-09-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberMapper memberMapper;

    @Override
    public List<MenuDto> getCurrentUserNav() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member user = memberService.getByUsername(username);

        List<Long> menuIds = memberMapper.getNavMenuIds(user.getMemberId());
        List<Menu> menus = this.listByIds(menuIds);

        // 转树状结构
        List<Menu> menuTree = buildTreeMenu(menus);

        // 实体转DTO
        return convert(menuTree);
    }

    @Override
    public List<Menu> tree() {
        // 获取所有菜单信息
        List<Menu> sysMenus = this.list(new QueryWrapper<Menu>().orderByAsc("orderNum"));

        // 转成树状结构
        return buildTreeMenu(sysMenus);
    }

    private List<MenuDto> convert(List<Menu> menuTree) {
        List<MenuDto> menuDtos = new ArrayList<>();

        menuTree.forEach(m -> {
            MenuDto dto = new MenuDto();

            dto.setMid(m.getId());
            dto.setName(m.getName());
            dto.setPart(m.getComponent());
            dto.setIcon(m.getIcon());
            dto.setEmpower(m.getPerms());
            dto.setPath(m.getPath());
            if (m.getChildren().size() > 0) {

                // 子节点调用当前方法进行再次转换
                dto.setChildren(convert(m.getChildren()));
            }

            menuDtos.add(dto);
        });

        return menuDtos;
    }

    private List<Menu> buildTreeMenu(List<Menu> menus) {

        List<Menu> finalMenus = new ArrayList<>();

        // 先各自寻找到各自的孩子
        for (Menu menu : menus) {

            for (Menu e : menus) {
                if (menu.getId() == e.getParentId()) {
                    menu.getChildren().add(e);
                }
            }

            // 提取出父节点
            if (menu.getParentId() == 0L) {
                finalMenus.add(menu);
            }
        }

        System.out.println(JSONUtil.toJsonStr(finalMenus));
        return finalMenus;
    }
}
