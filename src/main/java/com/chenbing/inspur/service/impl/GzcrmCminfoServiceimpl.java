package com.chenbing.inspur.service.impl;

import com.chenbing.inspur.mapper.GzcrmCminfoMapper;
import com.chenbing.inspur.pojo.GzcrmCminfo;
import com.chenbing.inspur.service.GzcrmCminfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
@Service
public class GzcrmCminfoServiceimpl implements GzcrmCminfoService {
@Autowired
private GzcrmCminfoMapper gzcrmCminfoMapper;
    @Override
    public List<GzcrmCminfo> queryMemberList(String deptName, String cminfoId, String cmName, String status) {
        List<GzcrmCminfo> list = new ArrayList<>();
        if (cminfoId != null && !cminfoId.equals("")) {
            list.add(this.gzcrmCminfoMapper.selectByPrimaryKey(Integer.parseInt(cminfoId)));
        }else {
            list = this.gzcrmCminfoMapper.queryMemberList("%"+deptName+"%",status,"%"+cmName+"%");
        }
        return list;
    }
    @Override
    public int insertCminfo(GzcrmCminfo gzcrmCminfo) {
        int num = this.gzcrmCminfoMapper.insert(gzcrmCminfo);
        return num;
    }
//    查看
@Override
public GzcrmCminfo queryCminfoById(int cmId) {
    return this.gzcrmCminfoMapper.selectByPrimaryKey(cmId);
}
    //单个删除
    @Override
    public int deleteCminfoById(int cmId) {
        int deleteByPrimaryKey = this.gzcrmCminfoMapper.deleteByPrimaryKey(cmId);
        return deleteByPrimaryKey;
    }

    @Override
    public int updateCminfo(GzcrmCminfo gzcrmCminfo) {
        return this.gzcrmCminfoMapper.updateByPrimaryKeySelective(gzcrmCminfo);
    }

    @Override
    public boolean deleteBatch(String checkedNum) {

            if (checkedNum != null && !checkedNum.equals("")) {
                String[] ids = checkedNum.split(",");
                for (int i = 0; i < ids.length; i++) {
                    this.gzcrmCminfoMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
                }
                return true;
            }else {
                return false;
            }
        }
    @Override
    public List<GzcrmCminfo> queryAll() {
        return this.gzcrmCminfoMapper.queryAll();
    }
    @Override
    public List<GzcrmCminfo> queryDetil(String cmUnit, String cmStatus, String cmSex, String cmEdu, String cmPL, String cmLevel) {
        List<GzcrmCminfo> list = gzcrmCminfoMapper.queryDetil("%"+cmUnit+"%",cmStatus,cmSex,cmEdu,cmPL,cmLevel);
        return list;
    }

}
