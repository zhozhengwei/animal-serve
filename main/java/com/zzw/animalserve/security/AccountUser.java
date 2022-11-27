package com.zzw.animalserve.security;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
@Data
@NoArgsConstructor
public class AccountUser implements UserDetails {

    private Long memberId;

    private String password;

    private String memberName;

    private Collection<? extends GrantedAuthority> authorities;



    public AccountUser(Long memberId, String memberName, String password, Collection<? extends GrantedAuthority> authorities) {
        this(memberId, memberName, password, true, true, true, true, authorities);
    }


    public AccountUser(Long memberId, String memberName, String password, boolean enabled, boolean accountNonExpired,
                       boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities) {
        Assert.isTrue(memberName != null && !"".equals(memberName) && password != null,
                "Cannot pass null or empty values to constructor");
        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.memberName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

