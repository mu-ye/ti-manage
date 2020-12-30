package com.huan.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("excel")
public class Excel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 工号
     */
    @TableField("job_number")
    private String jobNumber;

    /**
     * 生日
     */
    @TableField("birthday")
    private LocalDateTime birthday;

    /**
     * 是否生效 0： false  1: true
     */
    @TableField("alive")
    private Integer alive;

    /**
     * 创建时间
     */
    @TableField("creat_time")
    private LocalDateTime creatTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
