package com.yidong.chengguo_teacher.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.yidong.chengguo_teacher.entity.Patent;
import com.yidong.chengguo_teacher.entity.Teacher;
import com.yidong.chengguo_teacher.listener.ExcelListener;
import com.yidong.chengguo_teacher.mapper.PatentMapper;
import com.yidong.chengguo_teacher.service.PatentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author lzc
 * @date 2021/5/31 17 15
 * discription
 */
@Service
public class PatentServiceImpl implements PatentService {

    @Autowired
    private PatentMapper patentMapper;
    @Autowired
    private ExcelListener excelListener;

    @Override
    public void excelImport(MultipartFile file) throws IOException {

        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xls") && !file.getOriginalFilename().endsWith(".xlsx")) {
            return;
        }

        InputStream inputStream = new BufferedInputStream(file.getInputStream());
        //实例化实现了AnalysisEventListener接口的类
//        ExcelListener excelListener = new ExcelListener();

        ExcelReader reader = EasyExcelFactory.read(inputStream, excelListener).build();
//        读取信息
        ExcelReaderSheetBuilder sheetBuilder = new ExcelReaderSheetBuilder(reader);
        sheetBuilder.head(Teacher.class);
        reader.read(sheetBuilder.build());

    }

    @Override
    public int teacherCount(List<Teacher> teachers) {
        List<Patent> patentMan = patentMapper.findPatentMan();
        Set<String> set = new HashSet<>();
        for (Patent patent : patentMan) {
            String patent_man = patent.getPatent_man();
            if (patent_man.contains(",")) {
                String[] users = patent_man.split(",");
                set.addAll(Arrays.asList(users));
            } else {
                set.add(patent_man);
            }
        }
        System.out.println("专利相关人数：" + set.size());


        int count = 0;
        for (Teacher teacher : teachers) {
            String teacherName = teacher.getTeacherName();
            if (set.contains(teacherName)) {
                count++;
            }
        }


        return count;
    }
}
