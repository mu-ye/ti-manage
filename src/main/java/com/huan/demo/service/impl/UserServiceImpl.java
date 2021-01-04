package com.huan.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huan.demo.util.JwtTokenUtil;
import com.huan.demo.param.LoginParam;
import com.huan.demo.domain.User;
import com.huan.demo.exception.UserNameNotMatchPasswordException;
import com.huan.demo.mapper.RoleMapper;
import com.huan.demo.mapper.UserMapper;
import com.huan.demo.service.UserService;
import com.huan.demo.util.AesUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public List<String> getAccessTokenAndRefreshToken(LoginParam loginParam) {
        List<String> tokenList = new ArrayList<>();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",loginParam.getUsername())
                .eq("password", AesUtil.encrypt(loginParam.getPassword(), AesUtil.AES_KEY));
        User user = userMapper.selectOne(userQueryWrapper);
        if(user == null){
            System.out.println("抛出异常");
            throw new UserNameNotMatchPasswordException();
        }else {
            log.info("用户名密码验证成功，开始生成accessToken 和 refreshToken");
            List<String> RoleList = roleMapper.selectRolesByJobNumber(loginParam.getUsername());
            log.info("用户拥有权限 RoleList:{}",RoleList);
            String accessToken = JwtTokenUtil.generateAccessToken(loginParam.getUsername(),false,RoleList);
            String refreshToken = JwtTokenUtil.generateReFreshToken(loginParam.getUsername(),false);
            tokenList.add(accessToken);
            tokenList.add(refreshToken);
            // 将 refreshToken 保存到redis 缓存中, key为 用户唯一的工号  ,value 为 refreshToken
            stringRedisTemplate.opsForValue().set(loginParam.getUsername(),refreshToken);
            log.info("tokenList:{}",tokenList);
            return tokenList;
        }
    }
}
