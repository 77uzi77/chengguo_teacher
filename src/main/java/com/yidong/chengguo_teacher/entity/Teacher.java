package com.yidong.chengguo_teacher.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author lzc
 * @date 2021/5/31 17 21
 * discription
 */
@Data
public class Teacher {

    @ExcelProperty(index = 0)
    private String teacherName;
}
