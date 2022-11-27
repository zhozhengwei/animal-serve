package com.zzw.animalserve.security;

import cn.hutool.json.JSONUtil;
import com.zzw.animalserve.common.result.Result;

import com.zzw.animalserve.entity.Member;
import com.zzw.animalserve.service.MemberService;
import com.zzw.animalserve.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//登录成功的处理器
//@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    public JwtUtils jwtUtils;
    @Autowired
    public MemberService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();

        //生成jwt返回
        String username = authentication.getName();  //获得登录的用户名
        String jwt = jwtUtils.generateToken(username);
        response.setHeader(jwtUtils.getHeader(),jwt);

        Member user = userService.getByUsername(username);

        Result result = Result.succ(user);
        out.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
