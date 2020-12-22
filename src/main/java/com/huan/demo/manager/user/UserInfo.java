package com.huan.demo.manager.user;

import lombok.Data;

/**
 * @author 牟欢
 * @Classname UserInfo
 * @Description TODO
 * @Date 2020-12-08 9:24
 */
@Data
public class UserInfo {
    /**
     * 用户工号
     */
    public Integer id;

    /**
     * 员工姓名
     */
    public String name;

    /**
     * 部门Id
     */
    public Integer departmentId;

    /**
     * 部门名称
     */
    public String department;

}
