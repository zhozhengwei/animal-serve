package com.zzw.animalserve.config;

import com.zzw.animalserve.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  //启动方法级别的权限认证
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //继承WebSecurityConfigurerAdapter类，之后我们就可以进行重写配置来自定义功能了，不要忘了加上配置注解

//    @Autowired
//    LoginFailureHandler loginFailureHandler;

//    @Autowired
//    CaptchaFilter captchaFilter;

//    @Autowired
//    LoginSuccessHandler loginSuccessHandler;


    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


//    @Bean
//    JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
//        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager());
//        return jwtAuthenticationFilter;
//    }

    //Security中内置的BCryptPasswordEncoder，生成密码和匹配密码是否正确
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
//    @Autowired
//    JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    public static final String[] URL_WHITE_LIST = {
            "/member/loginAdmin",
            "/article/host",
            "/article/list",
            "/article/listAll",
            "/article/randomList",
            "/message/save",
            "/applyVolunteer/save",
            "/applyMember/save",
            "/article/searchId/{id}",
            "/article/memberHistory/{id}",
            "/activity/listAll/{pageNum}",
            "/activity/listPerson",
            "/articleComment/list/{id}",
            "/articleComment/save",
            "/activity/list",
            "/activity/listAll/{pageNum}",
            "/activity/history/{pageNum}",
            "/activity/searchId/{id}",
            "/article/updateLookCount/{id}",
            "/activityVideoInformation/list",
            "/donationRecord/save",
            "/donationRecord/count",
            "/participateInActivity/save",
            "/publication/listSearch",
            "/member/current",
            "/member/register",
            "/member/registerAdmin",
            "/member/info/{id}",
            "/member/update",
            "/member/list",
            "/tag/list",
            "/animalia/listAll",
            "/chordata/listAll/{id}",
            "/mammalia/listAll/{id}",
            "/artiodactyla/listAll/{id}",
            "/bovidae/listAll/{id}",
            "/bos/listAll/{id}",
            "/biology/listAll/{pageNum}",
            "/biology/listQuery",
            "/biology/searchId/{id}",
            "/message/listAnswer",
            "/imageInformation/upload",
            "/book/listAll/{pageNum}",
            "/book/listSearch",
            "/videoInformation/listQuery",
            "/publication/listAll/{pageNum}",
            "/interlinkage/listAll/{pageNum}",
            "/imageInformation/listQuery",
            "/imageInformation/listSearch",
            "/videoInformation/listTypeTwo",
            "/videoInformation/listTypeOne",
            "/alipay/**",
            "/auth/**",
            "/doc.html",
            "/doc.html*",
            "/webjars/**",
            "/img.icons/**",
            "/swagger-resources/**",
            "/**/v2/api-docs",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //.csrf()关闭CSRF防护.disable()
        //.cors()
//        http.cors().and().csrf().disable()
//                //登录配置
////                .formLogin()
////                .failureHandler(loginFailureHandler)  //登录失败
////                .successHandler(loginSuccessHandler)//登录成功
//
//                .and()
//                .logout()
//                .logoutSuccessHandler(jwtLogoutSuccessHandler) //正常退出的处理
//
//                //配置拦截规则
//                .and()
//                .authorizeRequests()//对所有请求的URL进行权限验证  链式编程，匹配具体的规则.authorizeRequests().规则
//                .antMatchers(URL_WHITE_LIST).permitAll()
//                .antMatchers("/member/login").anonymous()
//                //.antMatchers()请求放行的规则，采用白名单
//                // .permitAll():  对于所有人都应用这些规则
//                // .anyRequest()表示匹配所有的url请求
//                // .authenticated() 表示所匹配的URL都需要被认证才能访问
//                .anyRequest().authenticated()
//
//
//                //禁用session
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                /*ALWAYS : 总是创建HttpSession
//                  IF_REQUIRED : Spring Security只会在需要时创建一个HttpSession
//                  NEVER : Spring Security不会创建HttpSession，但如果它已经存在，将可以使用HttpSession
//                  STATELESS : Spring Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
//                */
//
//                //配置异常处理器
//                .and()
//                .exceptionHandling()
////                .authenticationEntryPoint(jwtAuthenticationEntryPoint)// 捕获到的是AuthenticationException，使用应的AuthenticationEntryPoint的commence()处理：
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedHandler(accessDeniedHandler)
//                //如果未登录转为AuthenticationException，否则无权限数据返回
//
//                //配合自定义过滤器
//                .and()
////                .addFilter(jwtAuthenticationFilter())   //验证jwt过滤器
//                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);  //验证 验证码
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //.antMatchers("/hello").permitAll()不需要验证token直接访问的接口，带token和不带都不会报错可以直接访问,使用permitAll()方法对静态的资源放行
                //对与登录接口 允许匿名访问  .antMatchers("/user/login","/hello").anonymous()两种访问的方式，前面字符是白名单（不需要登录验证就可以访问的）（未登录如果带有token会报错）
                .antMatchers(URL_WHITE_LIST).permitAll()
                .antMatchers("/member/login","/member/loginAdmin").anonymous()
                //除上面的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        //把token校验过滤器添加到过滤器链条中
        //添加过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //配置异常处理器
        http.exceptionHandling()
                //配置认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        //允许跨域
        http.cors();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
