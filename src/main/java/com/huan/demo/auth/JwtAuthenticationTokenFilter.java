/*
package com.huan.demo.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

*/
/**
 * @author 牟欢
 * @Classname JwtAuthenticationTokenFilter
 * @Description TODO
 * @Date 2020-09-17 13:48
 *//*

@Slf4j
@Service
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    JwtUserDetailsService jwtUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        // 在请求头中获取 jwtToken
        String jwtToken = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        if(!StringUtils.isEmpty(jwtToken)){
            // 截取掉其中的前缀  "Bearer " , .trim
            jwtToken = jwtToken.replace(JwtTokenUtil.TOKEN_PREFIX, "").trim();
            // 判断用户 token 是否过期，未过期继续鉴权；
            if(JwtTokenUtil.isTokenExpired(jwtToken)){
                log.info("用户 token 已过期");
                return;
                // 刷新 token
                //JwtTokenUtil.refreshToken(jwtToken);
            }else {
                // 从 jwtToken中 获取用户名
                String username = JwtTokenUtil.getUsernameFromToken(jwtToken);
                log.info("token 未过期");
                //如果可以正确的从JWT中提取用户信息，并且该用户未被授权
                if(username != null &&
                        SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
                    if(JwtTokenUtil.validateToken(jwtToken,userDetails.getUsername())){
                        //给使用该JWT令牌的用户进行授权
                        UsernamePasswordAuthenticationToken authenticationToken
                                = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                        //交给spring security管理,在之后的过滤器中不会再被拦截进行二次授权了
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }

                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
*/
