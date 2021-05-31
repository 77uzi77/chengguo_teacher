package com.yidong.chengguo_teacher.service;

import com.yidong.chengguo_teacher.entity.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author lzc
 * @date 2021/5/31 17 15
 * discription
 */

public interface PatentService {

    void excelImport(MultipartFile file) throws IOException;

    int teacherCount(List<Teacher> teachers);
}
