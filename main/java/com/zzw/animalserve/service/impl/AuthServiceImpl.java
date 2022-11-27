package com.zzw.animalserve.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.mapper.MemberMapper;
import com.zzw.animalserve.entity.Member;
import com.zzw.animalserve.service.AuthService;
import com.zzw.animalserve.service.EmailService;
import com.zzw.animalserve.entity.dto.EmailDto;
import com.zzw.animalserve.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Objects;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/10__2:56
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {

    // 验证码放入redis缓存过期时间
    @Value("${code.expiration}")
    private Long expiration;

    @Autowired
    private RedisCache redisCache;

    @Resource
    private EmailService emailService;

    @Resource
    private MemberMapper memberMapper;

    @Override
    public void sendMailCode(String email) {
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Member::getMemberEmail,email);
        Member user = memberMapper.selectOne(queryWrapper);
        // 查看注册邮箱是否存在
        if (!Objects.isNull(user)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "注册邮箱已存在");
        }

        // 获取发送邮箱验证码的HTML模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email-code.ftl");

        // 从redis缓存中尝试获取验证码
        Object code = redisCache.getCacheObject(email);
        if (code == null) {
            // 如果在缓存中未获取到验证码，则产生6位随机数，放入缓存中
            code = RandomUtil.randomNumbers(6);
            redisCache.setCacheObject(email, code);
            log.warn("这里是打印email"+email);
            log.warn("这里是打印code"+code);
            log.warn("这里是打印的缓存中的验证码"+redisCache.getCacheObject(email));
            if (!redisCache.expire(email,expiration)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "后台缓存服务异常");
            }
        }

        // 发送验证码
        emailService.send(new EmailDto(Collections.singletonList(email),
                "邮箱验证码", template.render(Dict.create().set("code", code))));


    }
}
