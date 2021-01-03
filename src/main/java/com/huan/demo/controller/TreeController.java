package com.huan.demo.controller;


import com.huan.demo.service.TreeService;
import com.huan.demo.vo.TreeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2020-12-18
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tree")
public class TreeController {

    private final TreeService treeService;

    /**
     * 查询目录结构
     *
     * @param level 第几级目
     * @return
     */
    @GetMapping("/getFirstLevelTreeVO")
    public List<TreeVO> getFirstLevelTreeVO(Integer level){
         return treeService.selectFirstLevelTreeVO(level);
    }


}

