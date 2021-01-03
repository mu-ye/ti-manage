package com.huan.demo.controller;

import com.huan.demo.auth.LoginParam;
import com.huan.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author mubaisama
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    /**
     * 用户名密码登录，获取 accessToken 和 refreshToken
     *
     * @param loginParam
     * @return accessToken  refreshToken
     */
    @PostMapping("/login")
    List<String> login(@Valid @RequestBody LoginParam loginParam){
        return userService.getAccessTokenAndRefreshToken(loginParam);
    }
}
