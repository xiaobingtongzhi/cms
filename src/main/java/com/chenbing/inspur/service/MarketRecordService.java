package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.GzcrmCmarketingRecord;
import org.springframework.stereotype.Service;

import java.util.List;
public interface MarketRecordService {
//    查询
    List<GzcrmCmarketingRecord> queryList(String mrId, String mrName);

    int insertMarketRecord(GzcrmCmarketingRecord gzcrmCmarketingRecord);

    GzcrmCmarketingRecord qureyMarketRecordById(int mrId);

    int updateMarketRecord(GzcrmCmarketingRecord gzcrmCmarketingRecord);
    int deleteRecordById(int mrId);

    boolean deleteMany(String checkTnum);
}
