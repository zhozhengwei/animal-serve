package com.zzw.animalserve.constant;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/9__16:45
 */
public interface MemberConstant {
    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "userLoginState";

    //  ------- 权限 --------

    /**
     * 默认权限
     */
    int DEFAULT_ROLE = 3;

    /**
     * 次级管理员权限
     */
    int ADMIN_ROLE = 2;

    /**
     * 超级管理员权限
     */
    int SUPER_ADMIN_ROLE = 1;
}
