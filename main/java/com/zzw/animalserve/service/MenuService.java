package com.zzw.animalserve.service;

import com.zzw.animalserve.entity.dto.MenuDto;
import com.zzw.animalserve.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Byterain
 * @since 2022-09-15
 */
public interface MenuService extends IService<Menu> {
    List<MenuDto> getCurrentUserNav();

    List<Menu> tree();
}
