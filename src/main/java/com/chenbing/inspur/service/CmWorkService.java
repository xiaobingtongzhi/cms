package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.GzcrmCmwork;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yuxi
 * @title: CmWorkService
 * @projectName CMIS
 * @description: TODO
 * @date 2020/7/6 22:08
 */
@Service("cmWorkService")
public interface CmWorkService {

    List<GzcrmCmwork> queryList(String cmId7, String cmPosition);

    int deleteCmWorkById(int cmworkId);

    boolean deleteMany(String checkTnum);

    GzcrmCmwork queryCmWorkById(int cmworkId);

    int update(GzcrmCmwork cmwork);

    int insert(GzcrmCmwork cmwork);

}
