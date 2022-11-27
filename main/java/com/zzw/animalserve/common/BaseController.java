package com.zzw.animalserve.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzw.animalserve.service.*;
import com.zzw.animalserve.utils.RedisCache;
import com.zzw.animalserve.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    public HttpServletRequest request;

    @Autowired
    public RedisUtil redisUtil;

    @Autowired
    public RoleService roleService;

    @Autowired
    public MenuService menuService;

    @Autowired
    public MemberService memberService;

    @Autowired
    public RoleMenuService roleMenuService;

    @Autowired
    public UserRoleService userRoleService;

    @Autowired
    public ChordataService chordataService;

    @Autowired
    public MammaliaService mammaliaService;

    @Autowired
    public ArtiodactylaService artiodactylaService;

    @Autowired
    public BovidaeService bovidaeService;

    @Autowired
    public BosService bosService;

    @Autowired
    public BiologyService biologyService;

    @Autowired
    public BiologyImageService biologyImageService;

    @Autowired
    public ImageInformationService imageInformationService;

    @Autowired
    public RedisCache redisCache;


    /**
     * 获取页面
     * @return
     */
    public Page getPage() {
        int current = ServletRequestUtils.getIntParameter(request, "cuurent", 1);
        int size = ServletRequestUtils.getIntParameter(request, "size", 10);
        return new Page(current, size);
    }
}
