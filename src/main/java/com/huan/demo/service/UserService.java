package com.huan.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huan.demo.param.LoginParam;
import com.huan.demo.domain.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-15
 */
public interface UserService extends IService<User> {
    /**
     * 用户名密码登录，获取 accessToken 和 refreshToken
     * @param loginParam
     * @return
     */
    List<String> getAccessTokenAndRefreshToken(LoginParam loginParam);
}
