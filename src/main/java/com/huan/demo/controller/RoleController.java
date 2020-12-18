package com.huan.demo.controller;


import com.huan.demo.domain.Role;
import com.huan.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-08
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {


    /**
     * <P>
     *     SpEL 表达式详解
     *     SpEL 添加到方法上有四种注解，内部搭配全局变量中的方法，如 hasRole  hasAnyRole 等
     *     1. @PreAuthorize("SpEL")  在方法执行执行，如果满足条件，则可以继续执行方法，否则会抛出 AccessDeniedException 异常
     *     2. @PostAuthorize("returnObject == authentication.name") 在方法执行后执行，
     *        只有返回值与 SecurityContextHolder.getContext().getAuthentication().getName() 相等才可以正常返回结果
     *     3. @PreFilter(filterTarget = "ids",value = "filterObject != 1") filterTarget 和参数保持一致， value 里面是判断条件，将满足条件的值传进去，不满足条件的值过滤掉
     *     4. @PostFilter("filterObject != authentication.name")  函数执行后进行过滤操作，满足过滤规则的可以正常返回
     * </P>
     */

    private final RoleService roleService;

    /**
     * 只有拥有 ROLE_ADMIN 权限的用户才可以执行该方法
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getList")
    List<Role> getList(){
        log.info(SecurityContextHolder.getContext().getAuthentication().getName());
        log.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        return roleService.list();
    }

    /**
     * 方法执行后进行判断， 如果返回值（或者返回值中的name属性 )和权限中的 authentication.name 相等，可以返回;  如果不相等，会报 AccessDeniedException 异常
     * @return
     */
    @PostAuthorize("returnObject == authentication.name")
    @GetMapping("/getUserName")
    public String getOne(){
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("username:{}",username);
        return "2";
    }

    /**
     * @PreFilter(filterTarget = "ids",value = "filterObject != 1") filterTarget 和参数保持一致， value 里面是判断条件，将满足条件的值传进去，不满足条件的值过滤掉
     * 比如 id = 1 的值 不允许被删除
     *
     * @param ids
     * @return
     */
    @PreFilter(filterTarget = "ids",value = "filterObject != 1")
    @GetMapping("/delete")
    public List<Integer> delete (@RequestParam("ids") List<Integer> ids){
        System.out.println(ids);
        return ids;
    }

    /**
     *
     * @PostFilter("filterObject != authentication.name")  函数执行后进行过滤操作，满足过滤规则的可以正常返回
     * @return
     */
    @PostFilter("filterObject != authentication.name")
    @GetMapping("/getRoleExceptAdmin")
    List<String> getRoleExceptAdmin(){
        List<String> stringList = new ArrayList<>();
        stringList.add("117042");
        stringList.add("117043");
        stringList.add("117044");
        return stringList;
    }

}

