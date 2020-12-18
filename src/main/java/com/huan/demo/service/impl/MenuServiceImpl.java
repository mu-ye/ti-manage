package com.huan.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huan.demo.domain.Menu;
import com.huan.demo.mapper.MenuMapper;
import com.huan.demo.service.MenuService;
import com.huan.demo.vo.MenuVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    public List<MenuVO> selectMenuVoByRole() {
        List<MenuVO> menuVOList = new ArrayList<>();
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        List<Menu> menuList =menuMapper.selectList(menuQueryWrapper);
        for(Menu menu : menuList){
            MenuVO menuVO = new MenuVO(menu);
            menuVOList.add(menuVO);
        }
        return menuVOList;
    }
}
