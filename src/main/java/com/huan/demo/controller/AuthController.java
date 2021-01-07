package com.huan.demo.controller;

import com.huan.demo.param.LoginParam;
import com.huan.demo.exception.RefreshTokenExpiredException;
import com.huan.demo.service.UserService;
import com.huan.demo.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author mubaisama
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    /**
     * 用户名密码登录，获取 accessToken 和 refreshToken
     *
     * @param loginParam
     * @return accessToken  refreshToken
     */
    @PostMapping("/login")
    List<String> login(@Valid @RequestBody LoginParam loginParam) {
        return userService.getAccessTokenAndRefreshToken(loginParam);
    }

    /**
     * 刷新 token
     *
     * @param request
     * @return
     */
    @GetMapping("/refreshToken")
    List<String> refreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader(JwtTokenUtil.TOKEN_HEADER).replace(JwtTokenUtil.TOKEN_PREFIX,"").trim();
        if (!StringUtils.isEmpty(refreshToken)) {
            // 去掉 token前缀 和 空格
            refreshToken = refreshToken.replace(JwtTokenUtil.TOKEN_PREFIX,"").trim();
            if(JwtTokenUtil.isTokenExpired(refreshToken)){
                // 判断 refreshToken 已过期，抛出异常
                log.info("refreshToken 已过期，请重新登录");
                throw new RefreshTokenExpiredException();
            } else {
                log.info("refreshToken 未过期，正在刷新token");
                // 根据前端传入 refreshToken 刷新 accessToken和refreshToken
                String accessToken = refreshToken.replace(JwtTokenUtil.TOKEN_PREFIX, "").trim();
                return JwtTokenUtil.refreshAccessToken(accessToken);
            }
        } else {
            throw new RefreshTokenExpiredException();
        }
    }
}
