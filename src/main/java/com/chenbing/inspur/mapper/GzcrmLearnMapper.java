package com.chenbing.inspur.mapper;

import com.chenbing.inspur.pojo.GzcrmLearn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GzcrmLearnMapper {
    int deleteByPrimaryKey(Integer lKey);

    int insert(GzcrmLearn record);

    int insertSelective(GzcrmLearn record);

    GzcrmLearn selectByPrimaryKey(Integer lKey);

    int updateByPrimaryKeySelective(GzcrmLearn record);

    int updateByPrimaryKey(GzcrmLearn record);
    //查询学习园地资料
    List<GzcrmLearn> queryLearnList(@Param("lName") String lname, @Param("lType") String ltype);
}