package com.yidong.chengguo_teacher.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yidong.chengguo_teacher.entity.Teacher;
import com.yidong.chengguo_teacher.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzc
 * @date 2021/5/31 17 20
 * discription
 */
@Component
public class ExcelListener extends AnalysisEventListener<Teacher> {
    private List<Teacher> data = new ArrayList<>();
    private static final int BATCH_COUNT = 3000;
    @Autowired
    private PatentService patentService;

//
//    public ExcelListener(TeacherDao userDao){
//        this.userDao = userDao;
//    }

    @Override
    public void invoke(Teacher user, AnalysisContext analysisContext) {
        //数据存储到data，供批量处理，或后续自己业务逻辑处理。
        data.add(user);
        //达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//        if(data.size() >= BATCH_COUNT){
//            saveData();
//            // 存储完成清理data
//            data.clear();
//        }
    }

//    private void saveData() {
//        for(Teacher user : data){
//            userDao.addTeacher(user);
//        }
//    }

    public List<Teacher> getData() {
        return data;
    }

    public void setData(List<Teacher> data) {
        this.data = data;
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println(data.size());
        int teacherCount = patentService.teacherCount(data);
        System.out.println("老师的数量:" + teacherCount);
    }
}