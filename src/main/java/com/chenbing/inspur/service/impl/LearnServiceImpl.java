package com.chenbing.inspur.service.impl;

import com.chenbing.inspur.mapper.GzcrmLearnMapper;
import com.chenbing.inspur.pojo.GzcrmLearn;
import com.chenbing.inspur.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Juny
 * @date 2020/7/17 - 10:07
 */
@Service
public class LearnServiceImpl implements LearnService {

    @Autowired
    private GzcrmLearnMapper gzcrmLearnMapper;

    @Override
    public List<GzcrmLearn> queryLearnList(String lname, String ltype) {
        List<GzcrmLearn> list;
        list = this.gzcrmLearnMapper.queryLearnList("%"+lname+"%",ltype);
        return list;
    }

    @Override
    public int insertLearn(GzcrmLearn gzcrmLearn) {
        return this.gzcrmLearnMapper.insert(gzcrmLearn);
    }

    @Override
    public int deleteLearnById(int learnId) {
        return gzcrmLearnMapper.deleteByPrimaryKey(learnId);
    }

    @Override
    public boolean deleteMany(String checkTnum) {
        if (checkTnum != null && !checkTnum.equals("")) {
            String[] ids = checkTnum.split(",");
            for (int i = 0; i < ids.length; i++) {
                gzcrmLearnMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public GzcrmLearn queryLearnById(int learnId) {
        return gzcrmLearnMapper.selectByPrimaryKey(learnId);
    }

    @Override
    public int updateLearn(GzcrmLearn gzcrmLearn) {
        return gzcrmLearnMapper.updateByPrimaryKeySelective(gzcrmLearn);
    }

}
