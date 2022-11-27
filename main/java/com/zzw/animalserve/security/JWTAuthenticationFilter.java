package com.zzw.animalserve.security;
import cn.hutool.core.util.StrUtil;
import com.zzw.animalserve.entity.Member;
import com.zzw.animalserve.service.MemberService;
import com.zzw.animalserve.utils.JwtUtils;
import com.zzw.animalserve.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeSet;
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceImpl userDetailService;

    @Autowired
    MemberService userService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("jwt校验的过滤器");
        String jwt = request.getHeader(jwtUtils.getHeader());
        if(StrUtil.isBlankOrUndefined(jwt)){

            chain.doFilter(request,response);
            return;
        }
        Claims claim = jwtUtils.getClaimByToken(jwt);
        if(chain==null){
            throw new JwtException("token异常");
        }
        if(jwtUtils.isTokenExpired(claim)){
            throw new JwtException("token已经过期");
        }
        String username = claim.getSubject();
        log.info("用户{}，正在进行系统登录",username);
        // 获取用户的权限等信息

        Member user = userService.getByUsername(username);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,null,userDetailService.getUserAuthority(user.getId()));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request,response);
    }
}
