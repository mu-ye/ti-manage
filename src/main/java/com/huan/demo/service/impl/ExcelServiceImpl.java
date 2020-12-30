package com.huan.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huan.demo.domain.Excel;
import com.huan.demo.mapper.ExcelMapper;
import com.huan.demo.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelServiceImpl extends ServiceImpl<ExcelMapper, Excel> implements ExcelService {

    private final ExcelMapper excelMapper;

    @Override
    public Boolean isEmpty(String jobNumber) {
        QueryWrapper<Excel> excelQueryWrapper = new QueryWrapper<>();
        excelQueryWrapper.eq("job_number", jobNumber);
        List<Excel> excelList = excelMapper.selectList(excelQueryWrapper);
        return excelList.isEmpty() ? true : false;
    }
}
