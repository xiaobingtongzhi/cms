package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.GzcrmCmrpr;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yuxi
 * @title: CmrprService
 * @projectName CMIS
 * @description: TODO
 * @date 2020/7/5 21:34
 */
@Service("cmrprService")
public interface CmrprService {

    List<GzcrmCmrpr> queryList(String cmId3, String cmType);

    int deleteCmrprById(int cmrprId);

    boolean deleteMany(String checkTnum);

    int insertCmrpr(GzcrmCmrpr cmrpr);

    GzcrmCmrpr queryCerById(int cmrprId);

    int update(GzcrmCmrpr cmrpr);

}
