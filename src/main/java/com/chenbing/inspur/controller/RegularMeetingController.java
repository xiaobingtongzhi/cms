package com.chenbing.inspur.controller;

import com.chenbing.inspur.pojo.GzcrmCmregular;
import com.chenbing.inspur.service.RegularMeetingService;
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
@RequestMapping("/regularMeeting")
public class RegularMeetingController {
    @Autowired
    private RegularMeetingService regularMeetingService;
//    会议记录查询
    @RequestMapping("/queryRegularList")
    public String queryRegularList(@RequestParam(required = false) String meetingId,
                                   @RequestParam(required = false) String meetingDate,
                                   @RequestParam(required = false) String meetingTheme,
                                   @RequestParam(required = false) String msg,
                                   @RequestParam(defaultValue = "1") int pageNum,
                                   Model model){

        PageHelper.startPage(pageNum,5);
        List<GzcrmCmregular> list = regularMeetingService.queryRegulaList(meetingId,meetingDate,meetingTheme);
        PageInfo<GzcrmCmregular> info = new PageInfo<>(list);
        model.addAttribute("meetingId",meetingId);
        model.addAttribute("meetingDate",meetingDate);
        model.addAttribute("meetingTheme",meetingTheme);
        model.addAttribute("msg",msg);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("info",info);

        return "manager/regularMeeting";
    }
//    记录添加
@RequestMapping("/insertRegular")
public String insertRegular(MultipartFile refile, GzcrmCmregular cmregular, Model model){

    if (!refile.isEmpty()&&refile!=null) {
        // 保存图片的路径，图片上传成功后，将路径保存到数据库
        String filePath = "D:\\IDEA\\cms\\src\\main\\webapp\\upload\\meetingFile";
        // 获取原始图片的扩展名
        String originalFilename = refile.getOriginalFilename();

        // 封装上传文件位置的全路径
        if (originalFilename != null && !originalFilename.equals("")) {
            File targetFile = new File(filePath, originalFilename);
            try {
                refile.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 保存路径到gzcrmCminfo
        cmregular.setCmAttachment(originalFilename);
    }

    int nums = regularMeetingService.insertRegular(cmregular);
    if (nums==1) {
        System.out.println("添加客户经理例会信息成功！");
        model.addAttribute("msg","<script>alert('添加客户经理例会信息成功！');</script>");
    }else {
        System.out.println("添加客户经理例会信息失败！！");
        model.addAttribute("msg","<script>alert('添加客户经理例会信息失败！');</script>");
    }


    return "redirect:/regularMeeting/queryRegularList";
}
//修改前的查询
@RequestMapping("/beforeUpdate")
public String beforeUpdate(int reId,Model model){

    GzcrmCmregular gzcrmCmregular = regularMeetingService.queryRegularById(reId);
    model.addAttribute("re",gzcrmCmregular);

    return "manager/regularMeetingUpdate";
}
//修改
    @RequestMapping("/updateRegular")
    public String updateRegular(MultipartFile refile,GzcrmCmregular cmregular,Model model){

        if (!refile.isEmpty()&&refile!=null) {
            // 保存图片的路径，图片上传成功后，将路径保存到数据库
            String filePath = "D:\\IDEA\\cms\\src\\main\\webapp\\upload\\meetingFile";
            // 获取原始图片的扩展名
            String originalFilename = refile.getOriginalFilename();

            // 封装上传文件位置的全路径
            if (originalFilename != null && !originalFilename.equals("")) {
                File targetFile = new File(filePath, originalFilename);
                try {
                    refile.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 保存路径到gzcrmCminfo
            cmregular.setCmAttachment(originalFilename);
        }

        int nums = regularMeetingService.updateRegular(cmregular);
        if (nums==1) {
            System.out.println("更新客户经理例会信息成功！");
            model.addAttribute("msg","<script>alert('更新客户经理例会信息成功！');</script>");
        }else {
            System.out.println("更新客户经理例会信息失败！！");
            model.addAttribute("msg","<script>alert('更新客户经理例会信息失败！');</script>");
        }

        return "redirect:/regularMeeting/queryRegularList";
    }
//    删除
@RequestMapping("/deleteRegular")
public String deleteRegular(int reId, Model model){

    int nums = regularMeetingService.deleteRegularById(reId);
    if (nums==1) {
        System.out.println("删除客户经理例会成功！");
        model.addAttribute("msg","<script>alert('删除客户经理例会成功！');</script>");
    }else {
        System.out.println("删除客户经理例会失败！！");
        model.addAttribute("msg","<script>alert('删除客户经理例会失败！');</script>");
    }

    return "redirect:/regularMeeting/queryRegularList";
}
//批量删除
    @RequestMapping("/deleteMany")
    public String deleteMany(String checkTnum,Model model){

        boolean flag = regularMeetingService.deleteMany(checkTnum);
        if (flag) {
            System.out.println("批量删除客户经理例会成功！");
            model.addAttribute("msg","<script>alert('批量删除客户经理例会成功');</script>");
        }else {
            System.out.println("批量删除客户经理例会失败！");
            model.addAttribute("msg","<script>alert('批量删除客户经理例会失败');</script>");
        }


        return "redirect:/regularMeeting/queryRegularList";
    }
}
