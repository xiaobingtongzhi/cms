package com.chenbing.inspur.mapper;

import com.chenbing.inspur.pojo.GzcrmCminfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GzcrmCminfoMapper {
    //查询客户经理信息
    List<GzcrmCminfo> queryMemberList(@Param("cmUnit") String deptName, @Param("status") String status, @Param("cmName")String cmName);
    int deleteByPrimaryKey(Integer cmId);

    int insert(GzcrmCminfo record);

    int insertSelective(GzcrmCminfo record);

    GzcrmCminfo selectByPrimaryKey(Integer cmId);

    int updateByPrimaryKeySelective(GzcrmCminfo record);

    int updateByPrimaryKey(GzcrmCminfo record);

    boolean deleteBatch(String checkedNum);

    List<GzcrmCminfo> queryAll();

    List<GzcrmCminfo> queryDetil(@Param("c1") String cmUnit, @Param("c2") String cmStatus, @Param("c3") String cmSex,@Param("c4") String cmEdu, @Param("c5") String cmPL, @Param("c6") String cmLevel);
}