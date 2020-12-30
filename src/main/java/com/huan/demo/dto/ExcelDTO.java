package com.huan.demo.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 牟欢
 * @Classname ExcelDTO
 * @Description TODO
 * @Date 2020-12-23 9:09
 */
@Data
public class ExcelDTO {
    /**
     * 年龄
     */
    @Excel(name = "年龄")
    private Integer age;

    /**
     * 工号
     */
    @Excel(name = "工号")
    private String jobNumber;

    /**
     * 生日
     */
    @Excel(name = "生日")
    private LocalDateTime birthday;
}
