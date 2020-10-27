package com.chenbing.inspur.service.impl;

import com.chenbing.inspur.mapper.GzcrmCmrprMapper;
import com.chenbing.inspur.pojo.GzcrmCmrpr;
import com.chenbing.inspur.service.CmrprService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yuxi
 * @title: CmrprServiceImpl
 * @projectName CMIS
 * @description: TODO
 * @date 2020/7/5 21:35
 */
@Service("cmrprService")
public class CmrprServiceImpl implements CmrprService {

    @Autowired
    private GzcrmCmrprMapper cmrprMapper;

    @Override
    public List<GzcrmCmrpr> queryList(String cmId3, String cmType) {
        return cmrprMapper.queryList(cmId3,"%"+cmType+"%");
    }

    @Override
    public int deleteCmrprById(int cmrprId) {
        return cmrprMapper.deleteByPrimaryKey(cmrprId);
    }

    @Override
    public boolean deleteMany(String checkTnum) {
        if (checkTnum != null && !checkTnum.equals("")) {
            String[] ids = checkTnum.split(",");
            for (int i = 0; i < ids.length; i++) {
                cmrprMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int insertCmrpr(GzcrmCmrpr cmrpr) {
        return cmrprMapper.insert(cmrpr);
    }

    @Override
    public GzcrmCmrpr queryCerById(int cmrprId) {
        return cmrprMapper.selectByPrimaryKey(cmrprId);
    }

    @Override
    public int update(GzcrmCmrpr cmrpr) {
        return cmrprMapper.updateByPrimaryKeySelective(cmrpr);
    }
}
