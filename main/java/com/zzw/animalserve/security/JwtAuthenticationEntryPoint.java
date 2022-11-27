package com.zzw.animalserve.security;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.result.Result;
import com.zzw.animalserve.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//没有实现登录，登录失败，返回的操作。
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        BaseResponse result = new BaseResponse(HttpStatus.UNAUTHORIZED.value(),"用户认证失败","请查询登录");
        String jsonString = JSON.toJSONString(result);
        //处理异常
        WebUtils.renderString(response,jsonString);
    }
}
