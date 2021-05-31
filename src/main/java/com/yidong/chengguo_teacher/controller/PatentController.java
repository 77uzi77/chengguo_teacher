package com.yidong.chengguo_teacher.controller;

import com.yidong.chengguo_teacher.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lzc
 * @date 2021/5/31 17 16
 * discription
 */
@RestController
@RequestMapping("patent")
public class PatentController {

    @Autowired
    private PatentService patentService;

    @PostMapping("/importExcel")
    public String excelImport(@RequestParam("file") MultipartFile file) throws IOException {
//        userService.excelImport(file);
        patentService.excelImport(file);
        return "success";
    }
}
