package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.GzcrmCmresults;

import java.util.List;

/**
 * @author Juny
 * @date 2020/7/15 - 8:07
 */
public interface ResultService {
    //查询工作业绩
    List<GzcrmCmresults> queryList(String cmId, String workYear);

    int insert(GzcrmCmresults cmresults);

    //根据ID查询客户经理工作业绩
    GzcrmCmresults queryResultById(int resId);

    //修改客户经理工作业绩
    int update(GzcrmCmresults cmresults);

    //删除客户经理业绩
    int deleteResult(int resId);

    //批量删除客户经理业绩
    boolean deleteMany(String checkedNum);
}
