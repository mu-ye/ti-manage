package com.huan.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huan.demo.domain.Tree;
import com.huan.demo.vo.TreeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-18
 */
public interface TreeService extends IService<Tree> {
    /**
     * 查询目录结构
     * @param level 要查询第几级下的数据
     * @return
     */
    List<TreeVO> selectFirstLevelTreeVO(Integer level);
}
