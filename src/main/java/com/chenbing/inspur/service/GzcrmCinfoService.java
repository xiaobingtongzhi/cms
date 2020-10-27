package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.GzcrmCinfo;

import java.util.List;

public interface GzcrmCinfoService {
    List<GzcrmCinfo> queryCinfoList(String cId, String cName, String cSsn);

    int insertCinfo(GzcrmCinfo gzcrmCinfo);
    GzcrmCinfo queryCinfoById(int cId);

    int updateCinfo(GzcrmCinfo gzcrmCinfo);
}
