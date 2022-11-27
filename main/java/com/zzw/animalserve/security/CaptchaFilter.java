package com.zzw.animalserve.security;

import com.zzw.animalserve.common.exception.CaptchaException;
import com.zzw.animalserve.common.lang.Const;
import com.zzw.animalserve.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//验证码的过滤器
@Slf4j
//@Component
public class CaptchaFilter extends OncePerRequestFilter {
    private final String loginURL = "#";
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    LoginFailureHandler loginFailureHandler;

    //过滤器的执行方法
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        if(loginURL.equals(url) && request.getMethod().equals("POST")){
            log.info("获取不到login链接，正在校验验证码--"+url);
            try {
                validate(request);
            } catch (CaptchaException e) {
                log.info(e.getMessage());
                loginFailureHandler.onAuthenticationFailure(request,response,e);
            }
        }
        filterChain.doFilter(request,response);
    }
    //具体验证的方法
    private void validate(HttpServletRequest request) throws CaptchaException {
        String code = request.getParameter("captchaCode");
        String key = request.getParameter("key");

        if(StringUtils.isBlank(code) || StringUtils.isBlank(key)){
            throw new CaptchaException("验证码不能为空");
        }
        log.info("redis的值{}---code的值{}",redisUtil.hget(Const.CAPTCHA_KEY,key),code);
        if(!code.equals(redisUtil.hget(Const.CAPTCHA_KEY,key))){
            throw new CaptchaException("验证码不正确");
        }
        redisUtil.hdel(Const.CAPTCHA_KEY,key);
    }
}
