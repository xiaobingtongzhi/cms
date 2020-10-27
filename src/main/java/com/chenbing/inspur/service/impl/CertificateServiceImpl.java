package com.chenbing.inspur.service.impl;

import com.chenbing.inspur.mapper.GzcrmCertificateMapper;
import com.chenbing.inspur.pojo.GzcrmCertificate;
import com.chenbing.inspur.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juny
 * @date 2020/7/17 - 8:17
 */
@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private GzcrmCertificateMapper certificateMapper;

    @Override
    public List<GzcrmCertificate> queryList(String cmId2, String cerName) {
        List<GzcrmCertificate> list = new ArrayList<>();

        if (cmId2 != null && !cmId2.equals("")) {
            list = this.certificateMapper.queryList(cmId2,"%"+cerName+"%");
        }else {
            list = this.certificateMapper.queryList(null,"%"+cerName+"%");
        }
        return list;
    }

    @Override
    public int insertCer(GzcrmCertificate certificate) {
        return this.certificateMapper.insert(certificate);
    }
    @Override
    public int deleteCerById(int cerId) {
        return certificateMapper.deleteByPrimaryKey(cerId);
    }

    @Override
    public boolean deleteMany(String checkTnum) {
        if (checkTnum != null && !checkTnum.equals("")) {
            String[] ids = checkTnum.split(",");
            for (int i = 0; i < ids.length; i++) {
                certificateMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public GzcrmCertificate queryCerById(int cerId) {
        return certificateMapper.selectByPrimaryKey(cerId);
    }

    @Override
    public int updateLearn(GzcrmCertificate certificate) {
        return certificateMapper.updateByPrimaryKeySelective(certificate);
    }
}
