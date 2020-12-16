package com.huan.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huan.demo.domain.Role;
import com.huan.demo.mapper.RoleMapper;
import com.huan.demo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-08
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    private  final RoleMapper roleMapper;

    @Override
    public List<Role> selectPageRole(Page<Role> page) {
        return roleMapper.selectPageRoleVo(page);
    }
}
