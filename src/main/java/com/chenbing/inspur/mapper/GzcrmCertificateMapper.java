package com.chenbing.inspur.mapper;

import com.chenbing.inspur.pojo.GzcrmCertificate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GzcrmCertificateMapper {
    int deleteByPrimaryKey(Integer cmKey);

    int insert(GzcrmCertificate record);

    int insertSelective(GzcrmCertificate record);

    GzcrmCertificate selectByPrimaryKey(Integer cmKey);

    int updateByPrimaryKeySelective(GzcrmCertificate record);

    int updateByPrimaryKey(GzcrmCertificate record);
    //查询证照
    List<GzcrmCertificate> queryList(@Param("p1") String cmId, @Param("p2") String cerName);
}