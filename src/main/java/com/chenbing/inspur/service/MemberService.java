package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.YcMember;

import java.util.List;

/**
 * @author Juny
 * @date 2020/7/2 - 16:09
 */

public interface MemberService {

    //登录
    List<YcMember> login(String username, String password);

    //查询用户
    List<YcMember> queryMemberList(Integer memId, String memName);
//
    //添加用户
    int insertMember(YcMember ycMember);

    //删除用户根据ID
    int deleteMemberByID(Integer memId);

//    //根据id查询客户
    YcMember queryMemberById(Integer memId);
//
//    //修改用户
   int updateMember(YcMember ycMember);
//
//    //批量删除用户
   boolean deleteBatch(String checkedNum);
//
//    //禁用/启用
  int updateStatusById(Integer memId, String status);
//
//    //重置密码
   int resetPasswordById(Integer memId);
}