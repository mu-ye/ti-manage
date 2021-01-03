/*
package com.huan.demo.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huan.demo.domain.Role;
import com.huan.demo.domain.User;
import com.huan.demo.mapper.RoleMapper;
import com.huan.demo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

*/
/**
 * @author 牟欢
 * @Classname JwtUserDetailsService
 * @Description TODO
 * @Date 2020-09-17 10:15ser
 *//*

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;


    */
/**
     * 根据 username 获取用户权限信息
     *
     * @param username 用户唯一凭证（可以是用户工号、用户名）
     * @return
     * @throws UsernameNotFoundException
     *//*

    @Override
    public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 根据用户名获取 用户信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        User loginUser = userMapper.selectOne(userQueryWrapper);
        // 获取用户权限信息
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        List<Role> roleList = roleMapper.selectList(roleQueryWrapper);
        // 添加权限
        for(Role role :roleList){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new JwtUserDetails(loginUser.getUsername(), loginUser.getPassword(), authorities);
    }
}
*/
