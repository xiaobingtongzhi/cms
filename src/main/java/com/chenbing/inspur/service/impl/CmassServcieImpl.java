package com.chenbing.inspur.service.impl;

import com.chenbing.inspur.mapper.GzcrmCmassMapper;
import com.chenbing.inspur.pojo.GzcrmCmass;
import com.chenbing.inspur.service.CmassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yuxi
 * @title: CmassServcieImpl
 * @projectName CMIS
 * @description: TODO
 * @date 2020/7/6 9:30
 */
@Service("cmassService")
public class CmassServcieImpl implements CmassService {

    @Autowired
    private GzcrmCmassMapper cmassMapper;

    @Override
    public List<GzcrmCmass> queryList(String cmId4, String cmassDate) {
        return cmassMapper.queryList(cmId4,cmassDate);
    }

    @Override
    public int deleteCmassById(int cmassId) {
        return cmassMapper.deleteByPrimaryKey(cmassId);
    }

    @Override
    public boolean deleteMany(String checkTnum) {
        if (checkTnum != null && !checkTnum.equals("")) {
            String[] ids = checkTnum.split(",");
            for (int i = 0; i < ids.length; i++) {
                cmassMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public GzcrmCmass queryCerById(int cmassId) {
        return cmassMapper.selectByPrimaryKey(cmassId);
    }

    @Override
    public int update(GzcrmCmass cmass) {
        return cmassMapper.updateByPrimaryKeySelective(cmass);
    }

    @Override
    public int insert(GzcrmCmass cmass) {
        return cmassMapper.insert(cmass);
    }
}
