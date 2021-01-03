/*
package com.huan.demo.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

*/
/**
 * @author 牟欢
 * @Classname JwtUserDetails
 * @Description TODO
 * @Date 2020-09-17 9:26
 *//*

@Data
public class JwtUserDetails implements UserDetails {
    String password;
    String username;
    */
/**
     * 用户的权限集合
     *//*

    Collection<? extends GrantedAuthority> authorities;
    */
/**
     * 是否没过期
     *//*

    boolean accountNonExpired;
    */
/**
     * 是否没被锁定
     *//*

    boolean accountNonLocked;
    */
/**
     * 是否没过期
     *//*

    boolean credentialsNonExpired;
    */
/**
     * 账号是否可用
     *//*

    boolean enabled;

    */
/**
     * 自定义构造函数
     *
     * @param username 用户名
     * @param password 密码
     * @param authorities 权限
     *//*

    public JwtUserDetails(String username, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return enabled;
    }
}
*/
