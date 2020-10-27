package com.chenbing.inspur.controller;

import com.chenbing.inspur.pojo.YcMember;
import com.chenbing.inspur.service.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Juny
 * @date 2020/7/4 - 9:49
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService meberService;

    @RequestMapping("/queryMemberList")
    public String queryMemberList(@RequestParam(required = false) Integer memId,
                                  @RequestParam(required = false) String memName,
                                  @RequestParam(required = false) String msg,
                                  @RequestParam(defaultValue="1") int pageNum,
                                  Model model) {
        PageHelper.startPage(pageNum,5);
        List<YcMember> list = this.meberService.queryMemberList(memId,memName);
        PageInfo<YcMember> info = new PageInfo<>(list);
        model.addAttribute("info",info);
        model.addAttribute("msg",msg);
        model.addAttribute("memId",memId);
        model.addAttribute("memName",memName);
        model.addAttribute("pageNum",pageNum);
        return "manager/userInfo";

    }
    @RequestMapping("/addMember")
    public String insertMember(YcMember ycMember, Model model){
        int num = this.meberService.insertMember(ycMember);
        if (num==1){
            model.addAttribute("msg","<script>alert('添加用户成功');</script>\")");
        }else {
            model.addAttribute("msg","<script>alert('添加用户失败');</script>\")");
        }

        return "redirect:/member/queryMemberList";
    }
////    删除用户
    @RequestMapping("/deleteMember")
    public String deleteMemberByID(Integer  memId, Model model){
        int num = this.meberService.deleteMemberByID(memId);
        if (num==1){
            model.addAttribute("msg","<script>alert('删除用户成功');</script>\")");
        }else {
            model.addAttribute("msg","<script>alert('删除用户失败');</script>\")");
        }

        return "redirect:/member/queryMemberList";
    }
//批量删除
    @RequestMapping("/deleteBatch")
    public String deleteBatchr(String checkedNum, Model model){
        boolean flag = this.meberService.deleteBatch(checkedNum);
        if (flag){
            model.addAttribute("msg","<script>alert('删除用户成功');</script>\")");
        }else {
            model.addAttribute("msg","<script>alert('删除用户失败');</script>\")");
        }

        return "redirect:/member/queryMemberList";
    }
//    修改查询
    @RequestMapping("/beforeUpdate")
    public String beforUpdatee(Integer memId,Model model){
        YcMember ycMember = this.meberService.queryMemberById(memId);
        model.addAttribute("ycMember",ycMember);
        return "manager/userInfoUpdate";
    }
//修改操作
    @RequestMapping("/updateMember")
    public String updateMember(YcMember  ycMember, Model model){
        int num = this.meberService.updateMember(ycMember);
        if (num==1){
            model.addAttribute("msg","<script>alert('修改用户成功');</script>\")");
        }else {
            model.addAttribute("msg","<script>alert('修改用户失败');</script>\")");
        }

        return "redirect:/member/queryMemberList";
    }
//状态修改
    @ResponseBody
    @RequestMapping("/updateIsEnable")
    public int updateIsEnable(Integer memId,String status,Model model){
        return this.meberService.updateStatusById(memId,status);
    }
    @RequestMapping("/resetPassword")
    public String resetPassword(Integer  memId, Model model){
        int num = this.meberService.resetPasswordById(memId);
        if (num==1){
            model.addAttribute("msg","<script>alert('重置密码成功');</script>\")");
        }else {
            model.addAttribute("msg","<script>alert('重置密码失败');</script>\")");
        }

        return "redirect:/member/queryMemberList";
    }
}
