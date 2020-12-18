package com.huan.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huan.demo.domain.Tree;
import com.huan.demo.mapper.TreeMapper;
import com.huan.demo.service.TreeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huan.demo.vo.TreeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-18
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TreeServiceImpl extends ServiceImpl<TreeMapper, Tree> implements TreeService {

    private static final Integer FIRST_LEVEL_PARENT_ID = 0;
    private final TreeMapper treeMapper;

    @Override
    public List<TreeVO> selectFirstLevelTreeVO(Integer level) {
        QueryWrapper<Tree> treeQueryWrapper = new QueryWrapper<>();
        treeQueryWrapper.eq("parent_id",level);
        List<TreeVO> treeVOList = new ArrayList<>();
        List<Tree> treeList = treeMapper.selectList(treeQueryWrapper);
        for(Tree tree : treeList){
            TreeVO treeVO = new TreeVO(tree);
            treeVOList.add(treeVO);
        }
        return treeVOList;
    }
}
