package com.huan.demo.interceptor;

import com.huan.demo.util.JwtTokenUtil;
import com.huan.demo.exception.ReFreshTokenException;
import com.huan.demo.exception.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
                // 根据 accessToken 中的 jobNumber 在redis 中获取 refreshToken
                String jobNumber =  JwtTokenUtil.getUsernameFromToken(accessToken);
                // 在redis 中查看 refreshToken 是否过期：未过期，刷新 accessToken 和 refreshToken； 已过期，用户重新登录

                ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
                if(stringRedisTemplate.hasKey(jobNumber)){
                    String refreshToken = operations.get(jobNumber);
                    if(JwtTokenUtil.isTokenExpired(refreshToken)){
                        log.info("refreshToken 过期");
                        throw new ReFreshTokenException();
                    }else {
                        log.info("refreshToken 未过期");
                        String newAccessToken = JwtTokenUtil.refreshAccessToken(accessToken);
                        throw new TokenExpiredException(newAccessToken);
                    }
                }else {
                    log.info("refreshToken 在Redis中过期或不存在");
                }
                throw new TokenExpiredException("accessToken 已过期");
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
