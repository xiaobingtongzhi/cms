package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.GzcrmCmltr;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yuxi
 * @title: CmltrService
 * @projectName CMIS
 * @description: TODO
 * @date 2020/7/6 11:26
 */
@Service("cmltrServcie")
public interface CmltrService {

    List<GzcrmCmltr> queryList(String cmId5, String cmTime, String cmSub);

    int deleteCmltrById(int cmltrId);

    boolean deleteMany(String checkTnum);

    int insertCmrpr(GzcrmCmltr cmltr);

    GzcrmCmltr queryCmltrById(int cmltrId);

    int update(GzcrmCmltr cmltr);

}
