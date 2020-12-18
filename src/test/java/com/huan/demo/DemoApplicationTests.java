package com.huan.demo;

import com.huan.demo.util.LogUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        LogUtil.customInfo("11111");
    }

}
