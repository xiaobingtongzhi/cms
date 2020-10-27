package com.chenbing.inspur.service.impl;

import com.chenbing.inspur.mapper.GzcrmCmworkMapper;
import com.chenbing.inspur.pojo.GzcrmCmwork;
import com.chenbing.inspur.service.CmWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yuxi
 * @title: CmWorkServiceImpl
 * @projectName CMIS
 * @description: TODO
 * @date 2020/7/6 22:09
 */
@Service("cmWorkService")
public class CmWorkServiceImpl implements CmWorkService {

    @Autowired
    private GzcrmCmworkMapper cmworkMapper;


    @Override
    public List<GzcrmCmwork> queryList(String cmId7, String cmPosition) {
        return cmworkMapper.queryList(cmId7,"%"+cmPosition+"%");
    }

    @Override
    public int deleteCmWorkById(int cmworkId) {
        return cmworkMapper.deleteByPrimaryKey(cmworkId);
    }

    @Override
    public boolean deleteMany(String checkTnum) {
        if (checkTnum != null && !checkTnum.equals("")) {
            String[] ids = checkTnum.split(",");
            for (int i = 0; i < ids.length; i++) {
                cmworkMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public GzcrmCmwork queryCmWorkById(int cmworkId) {
        return cmworkMapper.selectByPrimaryKey(cmworkId);
    }

    @Override
    public int update(GzcrmCmwork cmwork) {
        return cmworkMapper.updateByPrimaryKeySelective(cmwork);
    }

    @Override
    public int insert(GzcrmCmwork cmwork) {
        return cmworkMapper.insert(cmwork);
    }


}
