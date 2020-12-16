package com.huan.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huan.demo.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-08
 */
public interface RoleService extends IService<Role> {
    /**
     * 分页查询
     *
     * @param page 分页信息
     * @return
     */
    List<Role> selectPageRole(Page<Role> page);
}
