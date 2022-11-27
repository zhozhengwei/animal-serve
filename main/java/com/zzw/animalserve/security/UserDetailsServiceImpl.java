package com.zzw.animalserve.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.entity.Member;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.mapper.MemberMapper;
import com.zzw.animalserve.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeSet;

//登录的处理，同时登录后获得用户的信息以及 权限列表
@Slf4j
@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    MemberService userService;

    @Autowired
    MemberMapper memberDao;

    @Override
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        String emailMatcher="[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
        if(memberName.matches(emailMatcher)){
            LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Member::getMemberEmail,memberName);
            Member member = memberDao.selectOne(queryWrapper);

            if(member == null){
                log.info("member login failed, memberEmail cannot match userPassword");
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "没有该用户信息！你需要注册一个新的账号");
            }
            return new AccountUser(member.getMemberId(),member.getMemberEmail(),member.getPassword(),getUserAuthority(member.getMemberId()));
        }
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Member::getMemberName,memberName);
        Member member = memberDao.selectOne(queryWrapper);
        if(member==null){
            throw new UsernameNotFoundException("用户名或密码不正确");
        }

        return new AccountUser(member.getMemberId(),member.getMemberName(),member.getPassword(),getUserAuthority(member.getMemberId()));
    }

    /**
     * 获取用户权限信息（角色、菜单权限）
     * @param memberId
     * @return
     */
    public List<GrantedAuthority> getUserAuthority(Long memberId){

        // 角色(ROLE_admin)、菜单操作权限 sys:user:list
        /*
        "ROLE_user_manger,ROLE_admin,sys:manage,sys:user:list,sys:role:list,sys:menu:list,sys:tools,sys:dict:list,sys:role:save,sys:user:save,sys:user:update,sys:user:delete,sys:user:role,sys:user:repass,sys:role:update,sys:role:delete,sys:role:perm,sys:menu:save,sys:menu:update,sys:menu:delete"
        * */

        String authority = userService.getUserAuthorityInfo(memberId);  // ROLE_admin,ROLE_normal,sys:user:list,....

        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
