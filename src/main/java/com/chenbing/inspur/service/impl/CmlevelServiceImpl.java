package com.chenbing.inspur.service.impl;

import com.chenbing.inspur.mapper.GzcrmCmlevelMapper;
import com.chenbing.inspur.pojo.GzcrmCmlevel;
import com.chenbing.inspur.service.CmlevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yuxi
 * @title: CmlevelServiceImpl
 * @projectName CMIS
 * @description: TODO
 * @date 2020/7/6 16:14
 */
@Service("cmlevelService")
public class CmlevelServiceImpl implements CmlevelService {

    @Autowired
    private GzcrmCmlevelMapper cmlevelMapper;


    @Override
    public List<GzcrmCmlevel> queryList(String cmId6, String holdDate, String holdLevel) {
        return cmlevelMapper.queryList(cmId6,holdDate,"%"+holdLevel+"%");
    }

    @Override
    public int deleteCmlevelById(int cmlevelId) {
        return cmlevelMapper.deleteByPrimaryKey(cmlevelId);
    }

    @Override
    public boolean deleteMany(String checkTnum) {
        if (checkTnum != null && !checkTnum.equals("")) {
            String[] ids = checkTnum.split(",");
            for (int i = 0; i < ids.length; i++) {
                cmlevelMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public GzcrmCmlevel queryCmlevelById(int cmlevelId) {
        return cmlevelMapper.selectByPrimaryKey(cmlevelId);
    }

    @Override
    public int update(GzcrmCmlevel cmlevel) {
        return cmlevelMapper.updateByPrimaryKeySelective(cmlevel);
    }

    @Override
    public int insert(GzcrmCmlevel cmlevel) {
        return cmlevelMapper.insert(cmlevel);
    }


}
