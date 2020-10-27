package com.chenbing.inspur.controller;

import com.chenbing.inspur.pojo.GzcrmCminfo;
import com.chenbing.inspur.pojo.GzcrmCmresults;
import com.chenbing.inspur.service.GzcrmCminfoService;
import com.chenbing.inspur.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Juny
 * @date 2020/7/15 - 8:06
 */
@Controller
@RequestMapping("/results")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @Autowired
    private GzcrmCminfoService GzcrmCminfoService;

    @RequestMapping("/queryList")
    public String queryList(@RequestParam(required = false) String cmId1,
                            @RequestParam(required = false) String workYear,
                            @RequestParam(required = false) String msg,
                            Model model,
                            HttpSession session){

        List<GzcrmCminfo> cminfos = GzcrmCminfoService.queryAll();
        List<GzcrmCmresults> list = this.resultService.queryList(cmId1,workYear);
        model.addAttribute("cmId1",cmId1);
        model.addAttribute("workYear",workYear);
        model.addAttribute("results",list);
        //定位页面
        model.addAttribute("hash","tab1");
        model.addAttribute("msg",msg);
        model.addAttribute("cminfo",cminfos);

        return "manager/clientMgrInfoOther";
    }

    //添加年度工作业绩
    @RequestMapping("/insert")
    public String insert(GzcrmCmresults cmresults, Model model, HttpSession session){

        int flag = 0;
        System.out.println("cmId="+cmresults.getCmId());
        List<GzcrmCminfo> cminfos = GzcrmCminfoService.queryAll();
        System.out.println(cminfos.size());
        for (int i = 0; i < cminfos.size(); i++) {
            System.out.println(cminfos.get(i).getCmId());
            if (cminfos.get(i).getCmId().equals(cmresults.getCmId())) {
                System.out.println("进入");
                flag = 1;
            }
        }

        if (flag == 1) {
            cmresults.setCmModificationPerson((String)session.getAttribute("uname"));

            int nums = this.resultService.insert(cmresults);
            if (nums==1) {
                model.addAttribute("msg","<script>alert('添加年度工作业绩成功！');</script>");
            }else {
                model.addAttribute("msg","<script>alert('添加年度工作业绩失败！');</script>");
            }
        }else {
            model.addAttribute("msg","<script>alert('添加年度工作业绩失败，业务经理ID不存在！');</script>");
        }
        return "redirect:/results/queryList";



    }
    //修改工作业绩之前的查询工作
    @RequestMapping("/beforeUpdate")
    public String beforeUpdate(int resId, Model model){

//        document.getElementById('light2').style.display = 'block';
        List<GzcrmCminfo> cminfos = this.GzcrmCminfoService.queryAll();
        List<GzcrmCmresults> list = this.resultService.queryList(null,null);
        model.addAttribute("cminfo",cminfos);

        GzcrmCmresults gzcrmCmresults = this.resultService.queryResultById(resId);
        //要修改的工作业绩
        model.addAttribute("upResult",gzcrmCmresults);
        //要修改的工作业绩展示在light2模态框中
        model.addAttribute("light","light2");
        model.addAttribute("hash","tab1");
        model.addAttribute("results",list);
        model.addAttribute("cminfo",cminfos);


        return "manager/clientMgrInfoOther";
    }

    //修改工作业绩
    @RequestMapping("/update")
    public String update(GzcrmCmresults cmresults, Model model){

        int nums = this.resultService.update(cmresults);
        if (nums==1) {
            model.addAttribute("msg","<script>alert('更新年度工作业绩成功！');</script>");
        }else {
            model.addAttribute("msg","<script>alert('更新年度工作业绩失败！');</script>");
        }

        return "redirect:/results/queryList";
    }

    //删除工作业绩
    @RequestMapping("/deleteResult")
    @ResponseBody
    public int deleteResult(int resId, Model model){
        return this.resultService.deleteResult(resId);
    }

    @RequestMapping("/deleteBatch")
    public String deleteMany(String checkedNum, Model model){

        boolean flag = this.resultService.deleteMany(checkedNum);
        if (flag) {
            model.addAttribute("msg","<script>alert('批量删除年度工作业绩成功！');</script>");
        }else {
            model.addAttribute("msg","<script>alert('批量删除年度工作业绩失败！');</script>");
        }

        return "redirect:/results/queryList";

    }

}
