package com.chenbing.inspur.controller;

import com.chenbing.inspur.pojo.GzcrmCmarketingRecord;
import com.chenbing.inspur.service.MarketRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/marketRecord")
public class MarketRecordController {
@Autowired
   private MarketRecordService marketRecordService;
//查询
@RequestMapping("/queryList")
public String queryList(@RequestParam(required = false) String mrId,
                        @RequestParam(required = false) String mrName,
                        @RequestParam(required = false) String msg,
                        @RequestParam(defaultValue = "1") int pageNum,
                        Model model){
    PageHelper.startPage(pageNum,5);
    List<GzcrmCmarketingRecord> list = marketRecordService.queryList(mrId, mrName);
    PageInfo<GzcrmCmarketingRecord> info =new PageInfo<>(list);
    model.addAttribute("mrId",mrId);
    model.addAttribute("mrName",mrName);
    model.addAttribute("msg",msg);
    model.addAttribute("pageNum",pageNum);
    model.addAttribute("info",info);
    return "manager/marketRecord";
}
//添加数据
    @RequestMapping("/insertMarketRecord")
    public String insertMarketRecord(GzcrmCmarketingRecord gzcrmCmarketingRecord,
                                     MultipartFile report,
                                     MultipartFile recommendTable,
                                     MultipartFile evaluation,
                                     Model model){
        //处理客户拜访报告书
        if (!report.isEmpty()&&report!=null) {
            String filePath = "D:\\IDEA\\cms\\src\\main\\webapp\\upload\\marketRecord";
            String originalFilename = report.getOriginalFilename();

            if (originalFilename != null && !originalFilename.equals("")) {
                File targetFile = new File(filePath, originalFilename);
                try {
                    report.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            gzcrmCmarketingRecord.setcReport(originalFilename);
        }

        //处理客户推荐表
        if (!recommendTable.isEmpty()&&recommendTable!=null) {
            String filePath = "D:\\IDEA\\cms\\src\\main\\webapp\\upload\\marketRecord";
            String originalFilename = recommendTable.getOriginalFilename();

            if (originalFilename != null && !originalFilename.equals("")) {
                File targetFile = new File(filePath, originalFilename);
                try {
                    recommendTable.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            gzcrmCmarketingRecord.setcTestimonials(originalFilename);
        }

        //处理客户综合评价
        if (!evaluation.isEmpty()&&evaluation!=null) {
            String filePath = "D:\\IDEA\\cms\\src\\main\\webapp\\upload\\marketRecord";
            String originalFilename = evaluation.getOriginalFilename();

            if (originalFilename != null && !originalFilename.equals("")) {
                File targetFile = new File(filePath, originalFilename);
                try {
                    evaluation.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            gzcrmCmarketingRecord.setcEvaluation(originalFilename);
        }


        int nums =marketRecordService.insertMarketRecord (gzcrmCmarketingRecord);
        if (nums==1) {
            System.out.println("新增营销记录成功！");
            model.addAttribute("msg","<script>alert('新增营销记录成功！');</script>");
        }else {
            System.out.println("新增营销记录失败！！");
            model.addAttribute("msg","<script>alert('新增营销记录失败！');</script>");
        }



        return "redirect:/marketRecord/queryList";

    }
//    修改前的查询
    @RequestMapping("/beforeUpdate")
    public String beforeUpdate(int mrId,Model model){
        GzcrmCmarketingRecord gzcrmCmarketingRecord = marketRecordService.qureyMarketRecordById(mrId);
        model.addAttribute("mr",gzcrmCmarketingRecord);
        return "manager/marketRecordUpdate";
    }
//    修改
@RequestMapping("/update")
public String update(GzcrmCmarketingRecord gzcrmCmarketingRecord,
                     MultipartFile report,
                     MultipartFile recommendTable,
                     MultipartFile evaluation,
                     Model model){

    //处理客户拜访报告书
    if (!report.isEmpty()&&report!=null) {
        String filePath = "D:\\IDEA\\cms\\src\\main\\webapp\\upload\\marketRecord";
        String originalFilename = report.getOriginalFilename();

        if (originalFilename != null && !originalFilename.equals("")) {
            File targetFile = new File(filePath, originalFilename);
            try {
                report.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        gzcrmCmarketingRecord.setcReport(originalFilename);
    }

    //处理客户推荐表
    if (!recommendTable.isEmpty()&&recommendTable!=null) {
        String filePath = "D:\\IDEA\\cms\\src\\main\\webapp\\upload\\marketRecord";
        String originalFilename = recommendTable.getOriginalFilename();

        if (originalFilename != null && !originalFilename.equals("")) {
            File targetFile = new File(filePath, originalFilename);
            try {
                recommendTable.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        gzcrmCmarketingRecord.setcTestimonials(originalFilename);
    }

    //处理客户综合评价
    if (!evaluation.isEmpty()&&evaluation!=null) {
        String filePath = "D:\\IDEA\\cms\\src\\main\\webapp\\upload\\marketRecord";
        String originalFilename = evaluation.getOriginalFilename();

        if (originalFilename != null && !originalFilename.equals("")) {
            File targetFile = new File(filePath, originalFilename);
            try {
                evaluation.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        gzcrmCmarketingRecord.setcEvaluation(originalFilename);
    }


    int nums = marketRecordService.updateMarketRecord(gzcrmCmarketingRecord);
    if (nums==1) {
        System.out.println("修改营销记录成功！");
        model.addAttribute("msg","<script>alert('修改营销记录成功！');</script>");
    }else {
        System.out.println("修改营销记录失败！！");
        model.addAttribute("msg","<script>alert('修改营销记录失败！');</script>");
    }
    return "redirect:/marketRecord/queryList";
}
//单个删除
@RequestMapping("/deleteMarketRecord")
    public String deleteMarketRecord(int mrId,Model model){
    int num = marketRecordService.deleteRecordById(mrId);
    if (num==1){
        System.out.println("删除营销记录成功！");
        model.addAttribute("msg","<script>alert('删除营销记录成功！');</script>");
    }else {
        System.out.println("删除营销记录失败！！");
        model.addAttribute("msg","<script>alert('删除营销记录失败！');</script>");
    }

    return "redirect:/marketRecord/queryList";
}
//批量删除
    @RequestMapping("deleteMany")
    public String deleteMany(String checkTnum,Model model){
        boolean nums = marketRecordService.deleteMany(checkTnum);
        if (nums) {
            System.out.println("批量删除营销记录成功！");
            model.addAttribute("msg","<script>alert('批量删除营销记录成功');</script>");
        }else {
            System.out.println("批量删除营销记录失败！");
            model.addAttribute("msg","<script>alert('批量删除营销记录失败');</script>");
        }

        return "redirect:/marketRecord/queryList";
    }
}
