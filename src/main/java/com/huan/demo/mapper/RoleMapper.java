package com.huan.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huan.demo.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-08
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 单表分页查询 示例
     *
     * @param page 传入分页参数
     * @return
     */
    @Select("SELECT * FROM role")
    List<Role> selectPageRoleVo(Page<Role> page);
}
