package com.huan.demo;

import com.huan.demo.manager.user.UserInfoManager;
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

    @Test
    void getCount1(){
        System.out.println(userInfoManager.getUserInfoByJobNumber("117042"));
    }

}
