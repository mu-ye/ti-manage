package com.huan.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huan.demo.domain.Menu;
import com.huan.demo.vo.MenuVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-17
 */
public interface MenuService extends IService<Menu> {
    /**
     * 根据用户权限获取menu
     * @return
     */
    List<MenuVO> selectMenuVoByRole();

}
