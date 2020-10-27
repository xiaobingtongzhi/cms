package com.chenbing.inspur.controller;

import com.chenbing.inspur.pojo.GzcrmCinfo;
import com.chenbing.inspur.service.GzcrmCinfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cinfo")
public class GzcrmCinfoController {
@Autowired
private GzcrmCinfoService gzcrmCinfoService;
    //客户经理查询
    @RequestMapping("/queryCinfoList")
    public String queryMemberList(@RequestParam(required = false) String cId,
                                  @RequestParam(required = false) String cName,
                                  @RequestParam(required = false) String cSsn,
                                  @RequestParam(required = false) String msg,
                                  @RequestParam(defaultValue = "1") int pageNum,
                                  Model model){
        //PageHelper分页
        PageHelper.startPage(pageNum,5);
        List<GzcrmCinfo> gzcrmCinfos = gzcrmCinfoService.queryCinfoList(cId, cName, cSsn);
        PageInfo<GzcrmCinfo> info=new PageInfo<>(gzcrmCinfos);
        model.addAttribute("cCmId",cId);
        model.addAttribute("cName",cName);
        model.addAttribute("cSsn",cSsn);
        model.addAttribute("msg",msg);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("info",info);
        return "manager/clientinfo";
    }
//    插入数据
    @RequestMapping("/insertCinfo")
    public String insertCinfo(GzcrmCinfo gzcrmCinfo,Model model){
        int nums = gzcrmCinfoService.insertCinfo(gzcrmCinfo);
        if (nums==1) {
            System.out.println("插入客户信息成功！");
            model.addAttribute("msg","<script>alert('插入客户信息成功！');</script>");
        }else {
            System.out.println("插入客户信息失败！！");
            model.addAttribute("msg","<script>alert('插入客户信息失败！');</script>");
        }
        return "redirect:/cinfo/queryCinfoList";
    }
//   修改前的查询
    @RequestMapping("/beforeUpdate")
    public String beforeUpdate(int cId,Model model){
        GzcrmCinfo cinfo = gzcrmCinfoService.queryCinfoById(cId);
    model.addAttribute("cinfo",cinfo);
    return "manager/clientinfoUpdate";
    }
//    修改数据
    @RequestMapping("/updateCinfo")
    public String updateCinfo(GzcrmCinfo gzcrmCinfo,Model model){
        int i = gzcrmCinfoService.updateCinfo(gzcrmCinfo);
        if (i==1) {
            System.out.println("更新客户信息成功！");
            model.addAttribute("msg","<script>alert('更新客户信息成功！');</script>");
        }else {
            System.out.println("更新客户信息失败！！");
            model.addAttribute("msg","<script>alert('更新客户信息失败！');</script>");
        }

        return "redirect:/cinfo/queryCinfoList";
    }
}
