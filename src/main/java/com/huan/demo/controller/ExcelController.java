package com.huan.demo.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.huan.demo.dto.ExcelDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-23
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/excel")
public class ExcelController {

    /**
     * 从excel中导入数据到数据库
     *
     * @param uploadFile
     */
    @PostMapping("/readExcel")
    public void readExcel(@RequestParam("file") MultipartFile uploadFile) {
        log.info("上传机考成绩！");
        try {
            //存储并解析Excel
            File file = new File("C:/UploadFile/机考得分表.xlsx");
            uploadFile.transferTo(file);
            ImportParams importParams = new ImportParams();
            importParams.setHeadRows(2);
            List<ExcelDTO> computerTestResultExcelList = ExcelImportUtil.importExcel(file, ExcelDTO.class, importParams);
            log.info("{}", computerTestResultExcelList);
            for (ExcelDTO item : computerTestResultExcelList) {
                /**
                 * TODO: 写入数据库
                 */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

