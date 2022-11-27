package com.zzw.animalserve.security;

import com.alibaba.fastjson.JSON;
import com.zzw.animalserve.security.AccountUser;
import com.zzw.animalserve.utils.JwtUtil;
import com.zzw.animalserve.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @description(自定义过滤器)
 * @autor: zhouzhengwei
 * @date: 2022/8/28__0:46
 */
@Service
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userid = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = "login:"+userid;
        logger.warn("++++++++++++++++++++======="+redisKey);
//        AccountUser user = redisCache.getCacheObject(redisKey);
        AccountUser user = JSON.parseObject( redisCache.getCacheObject(redisKey).toString(), AccountUser.class);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder authentication英文释义为：身份验证，认证；鉴定
        // todo 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
