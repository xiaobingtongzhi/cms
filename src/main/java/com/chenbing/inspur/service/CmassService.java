package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.GzcrmCmass;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yuxi
 * @title: CmassService
 * @projectName CMIS
 * @description: TODO
 * @date 2020/7/6 9:29
 */
@Service("cmassService")
public interface CmassService {

    List<GzcrmCmass> queryList(String cmId4, String cmassDate);

    int deleteCmassById(int cmassId);

    boolean deleteMany(String checkTnum);

    GzcrmCmass queryCerById(int cmassId);

    int update(GzcrmCmass cmass);

    int insert(GzcrmCmass cmass);

}
