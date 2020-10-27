package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.GzcrmCmlevel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yuxi
 * @title: CmlevelService
 * @projectName CMIS
 * @description: TODO
 * @date 2020/7/6 16:12
 */
@Service("cmlevelService")
public interface CmlevelService {

    List<GzcrmCmlevel> queryList(String cmId6, String holdDate, String holdLevel);

    int deleteCmlevelById(int cmlevelId);

    boolean deleteMany(String checkTnum);

    GzcrmCmlevel queryCmlevelById(int cmlevelId);

    int update(GzcrmCmlevel cmlevel);

    int insert(GzcrmCmlevel cmlevel);

}
