package com.huan.demo.vo;

import com.huan.demo.domain.Menu;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 牟欢
 * @Classname MenuVO
 * @Description TODO
 * @Date 2020-12-17 10:09
 */
@Data
public class MenuVO {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 上级菜单编号，无上级菜单为 0
     */
    private Integer parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 重定向路径
     */
    private String redirect;

    /**
     * 组件
     */
    private String component;

    /**
     * meta 信息
     */
    private MenuMetaVO meta;

    /**
     * 自定义构造函数
     * @param menu menu
     */
    public MenuVO(Menu menu) {
        this.id = menu.getId();
        this.component = menu.getComponent();
        this.name = menu.getName();
        this.parentId = menu.getParentId();
        this.path = menu.getPath();
        this.redirect = StringUtils.isBlank(menu.getRedirect())?null:menu.getRedirect() ;
        this.meta = new MenuMetaVO(menu.getIcon(),menu.getTitle());
    }
}
