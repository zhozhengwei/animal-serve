package com.zzw.animalserve.service;

/**
 * @description(授权服务接口)
 * @autor: zhouzhengwei
 * @date: 2022/10/10__2:48
 */
public interface AuthService {
    /**
     * 向指定邮箱发送验证码
     *
     * @param email 邮箱号
     */
    void sendMailCode(String email);



}
