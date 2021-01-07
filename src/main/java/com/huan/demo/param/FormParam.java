package com.huan.demo.param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author 牟欢
 * @Classname FormParam
 * @Description TODO
 * @Date 2021-01-07 14:37
 */
@Data
public class FormParam {
    @NotBlank(message = "用户名不能为空")
    public String username;
    @NotBlank(message = "密码不能为空")
    public String sex;
    /*@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime date;
}
