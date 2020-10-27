package com.chenbing.inspur.service.impl;

import com.chenbing.inspur.mapper.GzcrmCmresultsMapper;
import com.chenbing.inspur.pojo.GzcrmCmresults;
import com.chenbing.inspur.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juny
 * @date 2020/7/15 - 8:08
 */
@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private GzcrmCmresultsMapper gzcrmCmresultsMapper;

    @Override
    public List<GzcrmCmresults> queryList(String cmId, String workYear) {
        List<GzcrmCmresults> list = new ArrayList<>();
        if (cmId != null && !cmId.equals("")) {
            return this.gzcrmCmresultsMapper.queryList(cmId,workYear);
        }
        return this.gzcrmCmresultsMapper.queryList(null,workYear);
    }

    @Override
    public int insert(GzcrmCmresults cmresults) {
        return this.gzcrmCmresultsMapper.insert(cmresults);
    }

    @Override
    public GzcrmCmresults queryResultById(int resId) {
        return this.gzcrmCmresultsMapper.selectByPrimaryKey(resId);
    }

    @Override
    public int update(GzcrmCmresults cmresults) {
        return this.gzcrmCmresultsMapper.updateByPrimaryKeySelective(cmresults);
    }

    @Override
    public int deleteResult(int resId) {
        return this.gzcrmCmresultsMapper.deleteByPrimaryKey(resId);
    }

    @Override
    public boolean deleteMany(String checkedNum) {
        if(checkedNum != null && !("").equals(checkedNum)){
            String[] ids = checkedNum.split(",");
            for (int i = 0; i < ids.length; i++) {
                this.gzcrmCmresultsMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
            }
            return true;
        }else{
            return  false;
        }
    }
}
