/*
package com.huan.demo.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

*/
/**
 * @author 牟欢
 * @Classname JwtAuthService
 * @Description TODO
 * @Date 2020-09-16 16:49
 *//*

@Slf4j
@Service
public class JwtAuthService {
    @Resource
    AuthenticationManager authenticationManager;

    */
/**
     * 登录认证换取JWT令牌
     *
     * @return JWT
     *//*

    public String login(String username, String password, Boolean rememberMe) {
        try {
            //使用用户名密码进行登录验证（数据库中的密码需要使用 BCryptPasswordEncoder 加密）
            UsernamePasswordAuthenticationToken upToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            //返回认证主体
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            throw new RuntimeException("用户名或者密码错误");
        }
        // 返回生成的 token
        return JwtTokenUtil.generateToken(username, rememberMe);
    }
}
*/
