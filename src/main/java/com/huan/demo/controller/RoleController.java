package com.huan.demo.controller;


import com.huan.demo.domain.Role;
import com.huan.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final RoleService roleService;
    @GetMapping("/getList")
    List<Role> getList(){
        log.info(SecurityContextHolder.getContext().getAuthentication().getName());
        log.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        return roleService.list();
    }

}

