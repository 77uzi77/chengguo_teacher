package com.yidong.chengguo_teacher.mapper;

import com.yidong.chengguo_teacher.entity.Patent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author lzc
 * @date 2021/5/31 17 50
 * discription
 */
@Mapper
@Repository
public interface PatentMapper {

    @Select("select patent_man from patent")
    List<Patent> findPatentMan();
}
