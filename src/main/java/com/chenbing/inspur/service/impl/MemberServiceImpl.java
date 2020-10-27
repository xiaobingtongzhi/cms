package com.chenbing.inspur.service.impl;


import com.chenbing.inspur.mapper.YcMemberMapper;
import com.chenbing.inspur.pojo.YcMember;
import com.chenbing.inspur.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private YcMemberMapper ycMemberMapper;

    @Override
    public List<YcMember> login(String username, String password) {
        List<YcMember> list = this.ycMemberMapper.login(username,password);
        return list;
    }

    @Override
    public List<YcMember> queryMemberList(Integer memId, String memName) {
        List<YcMember> list = new ArrayList<YcMember>();
        if(memId != null && !memId.equals("")){
            list.add(this.ycMemberMapper.selectByPrimaryKey(memId));
        }else{
            list = this.ycMemberMapper.queryMemberList("%"+ memName +"%");
        }
        return list;
    }

    @Override
    public int insertMember(YcMember ycMember) {
        int num = this.ycMemberMapper.insert(ycMember);
        return num;
    }

//    @Override
//    public int insertMember(YcMember ycMember) {
//        int num =  this.ycMemberMapper.insert(ycMember);
//        return num;
//    }
//
    @Override
    public int deleteMemberByID(Integer memId) {
        int num = this.ycMemberMapper.deleteByPrimaryKey(memId);
        return num;
    }

    @Override
    public YcMember queryMemberById(Integer memId) {
        YcMember ycMember= this.ycMemberMapper.selectByPrimaryKey(memId);
        return ycMember;
    }

    @Override
    public int updateMember(YcMember ycMember) {
        int num = this.ycMemberMapper.updateByPrimaryKeySelective(ycMember);
        return num;
    }

    @Override
    public boolean deleteBatch(String checkedNum) {
        if (checkedNum != null && !checkedNum.equals("")) {
            String[] ids = checkedNum.split(",");
            for (int i = 0; i < ids.length; i++) {
                this.ycMemberMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int updateStatusById(Integer memId, String status) {
        if ("T".equals(status)){
            status = "F";
        } else {
            status = "T";
        }
        return ycMemberMapper.updateStatusById(memId,status);
    }

    @Override
    public int resetPasswordById(Integer memId) {
        return this.ycMemberMapper.resetPasswordById(memId);
    }
}
