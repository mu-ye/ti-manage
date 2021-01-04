package com.huan.demo.interceptor;

import com.huan.demo.exception.AccessTokenExpiredException;
import com.huan.demo.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *     token 拦截器
 * </p>
 *
 *
 * @author mubaisama
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private final  StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 在请求头中获取token
        String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        log.info("token:{}",token);
        if(!StringUtils.isEmpty(token)){
            // 从请求头中截取有效的 accessToken
            String accessToken = token.replace(JwtTokenUtil.TOKEN_PREFIX,"").trim();
            if(JwtTokenUtil.isTokenExpired(accessToken)){
                log.info("accessToken 已过期");
                throw new AccessTokenExpiredException();
            }else {
                log.info("accessToken 未过期");
                String jobNumber = JwtTokenUtil.getUsernameFromToken(accessToken);
                List<String> role = JwtTokenUtil.getRolesFromToken(accessToken);
                log.info("jobNumber:{}",jobNumber);
                log.info("role:{}",role);
            }
        }else {
            // 请求中没有token, 前台登录
            log.info("请求中不含token,登录后再此访问");
        }
        return true;
    }
}
