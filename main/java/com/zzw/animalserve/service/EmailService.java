package com.zzw.animalserve.service;

import com.zzw.animalserve.entity.dto.EmailDto;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/10__2:16
 */
public interface EmailService {
    /**
     * 发送邮件
     * @param emailDto 邮箱列表
     */
    void send(EmailDto emailDto);
}
