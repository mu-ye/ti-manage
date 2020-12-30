package com.huan.demo.service;

import com.huan.demo.domain.Excel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-23
 */
public interface ExcelService extends IService<Excel> {
    /**
     * 工号为 jobNumber 的记录是否存在
     * @param jobNumber 工号 唯一标识
     * @return
     */
    Boolean isEmpty(String jobNumber);
}
