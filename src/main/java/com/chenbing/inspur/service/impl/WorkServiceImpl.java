package com.chenbing.inspur.service.impl;

import com.chenbing.inspur.mapper.GzcrmCminfoWorkMapper;
import com.chenbing.inspur.pojo.GzcrmCminfoWork;
import com.chenbing.inspur.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yuxi
 * @title: WorkServiceImpl
 * @projectName CMIS
 * @description: TODO
 * @date 2020/7/3 11:32
 */
@Service("workService")
public class WorkServiceImpl implements WorkService {

    @Autowired
    private GzcrmCminfoWorkMapper workMapper;

    @Override
    public int deleteWorkById(int workId) {
        return workMapper.deleteByPrimaryKey(workId);
    }

    @Override
    public boolean deleteMany(String checkTnum) {
        if (checkTnum != null && !checkTnum.equals("")) {
            String[] ids = checkTnum.split(",");
            for (int i = 0; i < ids.length; i++) {
                workMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int insertWork(GzcrmCminfoWork gzcrmCminfoWork) {
        return workMapper.insert(gzcrmCminfoWork);
    }

    @Override
    public int updateWork(GzcrmCminfoWork gzcrmCminfoWork) {
        return workMapper.updateByPrimaryKeySelective(gzcrmCminfoWork);
    }

    @Override
    public List<GzcrmCminfoWork> queryList(String workKey, String inDate) {
        return workMapper.queryList(workKey,inDate);
    }

    @Override
    public GzcrmCminfoWork queryWorkRecordById(int workId) {
        return workMapper.selectByPrimaryKey(workId);
    }

}
