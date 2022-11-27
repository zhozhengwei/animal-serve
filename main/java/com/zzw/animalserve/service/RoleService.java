package com.zzw.animalserve.service;

import com.zzw.animalserve.entity.Role;
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
public interface RoleService extends IService<Role> {
    //通过用户编号 查询该用户的角色信息
    public List<Role> listRolesByUserId(Long userId);
}
