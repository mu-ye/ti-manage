package com.huan.demo;

import com.huan.demo.manager.user.UserInfoManager;
import com.huan.demo.mapper.RoleMapper;
import com.huan.demo.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
class DemoApplicationTests {
    @Autowired
    UserInfoManager userInfoManager;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleService roleService;

    @Test
    void getCount1(){
        System.out.println(userInfoManager.getUserInfoByJobNumber("117042"));
    }

    @Test
    void getCount2(){
        //System.out.println(roleMapper.selectRolesByJobNumber("117042"));
        System.out.println(roleService.selectRolesByJobNumber("117042"));
    }

}
