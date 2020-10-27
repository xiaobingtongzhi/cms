package com.chenbing.inspur.service.impl;

import com.chenbing.inspur.mapper.GzcrmCinfoMapper;
import com.chenbing.inspur.pojo.GzcrmCinfo;
import com.chenbing.inspur.service.GzcrmCinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GzcrmCinfoServiceimpl implements GzcrmCinfoService {

    @Autowired
    private GzcrmCinfoMapper cinfoMapper;
    @Override
    public List<GzcrmCinfo> queryCinfoList(String cId, String cName, String cSsn) {
        List<GzcrmCinfo> list = new ArrayList<>();

        if (cId != null && !cId.equals("")) {
            GzcrmCinfo tmp = cinfoMapper.selectByPrimaryKey(Integer.parseInt(cId));
            if (tmp != null) {
                list.add(tmp);
            }
        }else {
            list = cinfoMapper.queryCinfoList("%"+cName+"%","%"+cSsn+"%");
        }

        return list;
    }

    @Override
    public int insertCinfo(GzcrmCinfo gzcrmCinfo) {
        return cinfoMapper.insert(gzcrmCinfo);
    }

    @Override
    public GzcrmCinfo queryCinfoById(int cId) {
        return cinfoMapper.selectByPrimaryKey(cId);
    }

    @Override
    public int updateCinfo(GzcrmCinfo gzcrmCinfo) {
        return cinfoMapper.updateByPrimaryKey(gzcrmCinfo);
    }
}
