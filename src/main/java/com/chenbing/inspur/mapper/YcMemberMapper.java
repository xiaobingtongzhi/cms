package com.chenbing.inspur.mapper;

import com.chenbing.inspur.pojo.YcMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YcMemberMapper {
    //用户登录
    List<YcMember> login(@Param("cname") String username, @Param("memPass") String password);
    int deleteByPrimaryKey(Integer memId);
    //用户查询
    List<YcMember> queryMemberList(@Param("cname")String memName);
    int insert(YcMember record);

    int insertSelective(YcMember record);

    YcMember selectByPrimaryKey(Integer memId);

    int updateByPrimaryKeySelective(YcMember record);

    int updateByPrimaryKey(YcMember record);

    int updateStatusById(@Param("memId") Integer memId,@Param("status") String status);

    int resetPasswordById(@Param("memId") Integer memId);
}