package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.GzcrmCminfoWork;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yuxi
 * @title: WorkService
 * @projectName CMIS
 * @description: TODO
 * @date 2020/7/3 11:30
 */
@Service("workService")
public interface WorkService {

    int deleteWorkById(int workId);

    boolean deleteMany(String checkTnum);

    int insertWork(GzcrmCminfoWork gzcrmCminfoWork);

    int updateWork(GzcrmCminfoWork gzcrmCminfoWork);

    List<GzcrmCminfoWork> queryList(String workKey, String inDate);

    GzcrmCminfoWork queryWorkRecordById(int workId);
}
