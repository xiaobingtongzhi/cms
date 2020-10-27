package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.GzcrmCminfo;
import com.chenbing.inspur.pojo.YcMember;

import java.util.List;

public interface GzcrmCminfoService {
    //查询用户
    List<GzcrmCminfo> queryMemberList(String deptName, String cminfoId, String cmName, String status);
    //    添加数据
    int insertCminfo(GzcrmCminfo gzcrmCminfo);
    //修改之前根据ID查询出要修改的客户经理
    GzcrmCminfo queryCminfoById(int cmId);
    List<GzcrmCminfo> queryAll();
    int deleteCminfoById(int cmId);

    int updateCminfo(GzcrmCminfo gzcrmCminfo);

    boolean deleteBatch(String checkedNum);

    List<GzcrmCminfo> queryDetil(String cmUnit, String cmStatus, String cmSex, String cmEdu, String cmPL, String cmLevel);
}
