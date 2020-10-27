package com.chenbing.inspur.mapper;

import com.chenbing.inspur.pojo.GzcrmCmarketingRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GzcrmCmarketingRecordMapper {
    int deleteByPrimaryKey(Integer cKey);

    int insert(GzcrmCmarketingRecord record);

    int insertSelective(GzcrmCmarketingRecord record);

    GzcrmCmarketingRecord selectByPrimaryKey(Integer cKey);

    int updateByPrimaryKeySelective(GzcrmCmarketingRecord record);

    int updateByPrimaryKey(GzcrmCmarketingRecord record);

    List<GzcrmCmarketingRecord> queryList(@Param("p1") String mrName);
}