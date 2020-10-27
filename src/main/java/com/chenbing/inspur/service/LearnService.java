package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.GzcrmLearn;

import java.util.List;

/**
 * @author Juny
 * @date 2020/7/17 - 10:06
 */
public interface LearnService {
    List<GzcrmLearn> queryLearnList(String lname, String ltype);

    //添加学习材料
    int insertLearn(GzcrmLearn gzcrmLearn);
//    删除
    int deleteLearnById(int learnId);
//批量删除
    boolean deleteMany(String checkTnum);
//    修改前的查询
GzcrmLearn queryLearnById(int learnId);
//    修改
    int updateLearn(GzcrmLearn gzcrmLearn);
}
