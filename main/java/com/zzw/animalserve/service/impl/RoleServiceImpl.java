package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzw.animalserve.entity.Role;
import com.zzw.animalserve.mapper.RoleMapper;
import com.zzw.animalserve.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public List<Role> listRolesByUserId(Long userId) {

        List<Role> sysRoles = this.list(new QueryWrapper<Role>()
                .inSql("id", "select role_id from sys_member_role where member_id = " + userId));
        return sysRoles;
    }
}
