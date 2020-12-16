package com.huan.demo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huan.demo.domain.Role;
import com.huan.demo.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 牟欢
 * @Classname MybatisPlusTest
 * @Description TODO
 * @Date 2020-12-15 15:06
 */
@SpringBootTest
public class MybatisPlusTest {
    @Autowired
    RoleServiceImpl roleService;
    @Test
    void contextLoads() {
        Page<Role> page = new Page<>();
        page.setCurrent(1);
        page.setSize(5);
        System.out.println(roleService.selectPageRole(page));
    }
}
