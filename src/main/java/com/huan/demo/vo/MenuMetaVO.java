package com.huan.demo.vo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 牟欢
 * @Classname MenuMetaVO
 * @Description TODO
 * @Date 2020-12-17 10:10
 */
@Data
public class MenuMetaVO {
    /**
     * meta属性 图标
     */
    private String icon;

    /**
     * meta属性 导航名称
     */
    private String title;

    /**
     * 自定义构造函数
     *
     * @param icon  icon
     * @param title title
     */
    public MenuMetaVO(String icon, String title) {
        this.icon = StringUtils.isBlank(icon) ? null : icon;
        this.title = title;
    }
}
