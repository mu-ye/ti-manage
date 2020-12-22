package com.huan.demo.manager.user;

import com.huan.demo.manager.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author 牟欢
 * @Classname UserInfoManager
 * @Description TODO
 * @Date 2020-12-07 16:27
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserInfoManager {
    /**
     * 注意 在 RestTemplate中要将 RestTemplate 注入进 Bean
     */
    private final RestTemplate restTemplate;
    private static final String BASE_GET_USER_INFO_URL = "http://112.4.141.91:8210/opens/employees/";

    /**
     * 根据员工工号，查询员工信息
     *
     * @param jobNumber 工号
     * @return
     */
    public UserInfo getUserInfoByJobNumber(String jobNumber) {
        ResponseEntity<Result<UserInfo>> responseEntity = restTemplate.exchange(BASE_GET_USER_INFO_URL + jobNumber, HttpMethod.GET, null, new ParameterizedTypeReference<Result<UserInfo>>() {});
        return responseEntity.getBody().getData();
    }

}
