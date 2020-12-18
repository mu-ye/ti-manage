package com.huan.demo.vo;

import com.huan.demo.domain.Tree;
import lombok.Data;

/**
 * @author 牟欢
 * @Classname TreeVO
 * @Description TODO
 * @Date 2020-12-18 13:41
 */
@Data
public class TreeVO {
    /**
     * 节点唯一id  key
     */
    private Integer key;

    /**
     * 节点名称
     */
    private String title;

    /**
     * 是否为叶子节点
     */
    private Boolean isLeaf;

    public TreeVO() {
    }

    /**
     * 将返回数据库查询的 tree 封装为 treeVO
     * @param tree
     */
    public TreeVO(Tree tree) {
        this.key = tree.getId();
        this.title = tree.getTitle();
        this.isLeaf = tree.getIsLeaf().equals(1) ? true : false;
    }
}
