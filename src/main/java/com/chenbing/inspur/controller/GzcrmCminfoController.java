package com.chenbing.inspur.controller;

import com.chenbing.common.util.DateUtils;
import com.chenbing.common.util.GeneratorIDUntils;
import com.chenbing.inspur.pojo.GzcrmCminfo;
import com.chenbing.inspur.pojo.YcMember;
import com.chenbing.inspur.service.GzcrmCminfoService;
import com.chenbing.inspur.service.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/cminfo")
public class GzcrmCminfoController {

    @Autowired
    private GzcrmCminfoService gzcrmCminfoService;
//客户经理查询
    @RequestMapping("/queryMemberList")
    public String queryMemberList(@RequestParam(required = false) String deptName,
                                  @RequestParam(required = false) String cminfoId,
                                  @RequestParam(required = false) String status,
                                  @RequestParam(required = false) String cmName,
                                  @RequestParam(required = false) String msg,
                                  @RequestParam(defaultValue = "1") int pageNum,
                                  Model model){

        //PageHelper分页
        PageHelper.startPage(pageNum,5);
        List<GzcrmCminfo> list = this.gzcrmCminfoService.queryMemberList(deptName,cminfoId,cmName,status);
        PageInfo<GzcrmCminfo> info = new PageInfo<>(list);
        model.addAttribute("deptName",deptName);
        model.addAttribute("cminfoId",cminfoId);
        model.addAttribute("status",status);
        model.addAttribute("msg",msg);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("cmName",cmName);
        model.addAttribute("info",info);

        return "manager/clientMgr";
    }
//批量导入
    @RequestMapping("/exportExcle")
    public void exportExcle(@RequestParam(required = false) String deptName,
                            @RequestParam(required = false) String cminfoId,
                            @RequestParam(required = false) String status,
                            @RequestParam(required = false) String cmName,
                            HttpSession session,
                            HttpServletResponse response,
                            Model model){
        String name = (String)session.getAttribute("uname");
        List<GzcrmCminfo> list = gzcrmCminfoService.queryMemberList(deptName, cminfoId, cmName, status);
        response.setCharacterEncoding("UTF-8");

        //生成excel
        XSSFWorkbook xssf = new XSSFWorkbook();
        Sheet sheet = xssf.createSheet();
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("客户经理编号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("性别");
        row.createCell(3).setCellValue("身份证号");
        row.createCell(4).setCellValue("出生日期");
        row.createCell(5).setCellValue("客户经理等级");
        row.createCell(6).setCellValue("机构");
        row.createCell(7).setCellValue("部门");

        //记录行数
        int index = 1;
        for (int i = 0; i < list.size(); i++) {
            GzcrmCminfo tmp = list.get(i);
            Row tmpRow = sheet.createRow(i+1);
            tmpRow.createCell(0).setCellValue(tmp.getCmId());
            tmpRow.createCell(1).setCellValue(tmp.getCmName());
            tmpRow.createCell(2).setCellValue(tmp.getCmSex());
            tmpRow.createCell(3).setCellValue(tmp.getCmSsn());
            tmpRow.createCell(4).setCellValue(DateUtils.format(tmp.getCmBirthday()));
            tmpRow.createCell(5).setCellValue(tmp.getCmLevel());
            tmpRow.createCell(6).setCellValue(tmp.getCmUnit());
            tmpRow.createCell(7).setCellValue(tmp.getCmDept());
        }
        try {
            String date = "_"+DateUtils.getSysDate().replaceAll("-","");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String("客户经理名单_".getBytes(),"iso-8859-1")+name+".xlsx");
            OutputStream ouputStream = response.getOutputStream();
            xssf.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//    添加用户数据
    @RequestMapping("/insertCminfo")
    public String insertCminfo(GzcrmCminfo gzcrmCminfo,
                               MultipartFile picture,
                               Model model,
                               HttpSession session){

        gzcrmCminfo.setCmStatus("T");

        // 保存图片的路径，图片上传成功后，将路径保存到数据库
        String filePath = "D:\\tomcat\\apache-tomcat-8.5.54-windows-x64\\apache-tomcat-8.5.54\\webapps\\cms_war\\upload\\cminfoPic";
        // 获取原始图片的扩展名
        String originalFilename = picture.getOriginalFilename();

        // 生成文件新的名字
        String newFileName = GeneratorIDUntils.getDateTime() +"."+ originalFilename.split("\\.")[1];
        // 封装上传文件位置的全路径
        File targetFile = new File(filePath, newFileName);
        try {
            picture.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 保存路径到gzcrmCminfo
        gzcrmCminfo.setCmPhoto(newFileName);

        gzcrmCminfo.setCmModificationPerson((String)session.getAttribute("uname"));

        int nums = this.gzcrmCminfoService.insertCminfo(gzcrmCminfo);
        if (nums==1) {
            model.addAttribute("msg","<script>alert('添加客户经理成功！');</script>");
        }else {
            model.addAttribute("msg","<script>alert('添加客户经理失败！');</script>");
        }

        return "redirect:/cminfo/queryMemberList";
    }
//    查看
@RequestMapping("/beforeLook")
public String beforeLook(int cmId,Model model) {

    GzcrmCminfo cminfo = gzcrmCminfoService.queryCminfoById(cmId);
    System.out.println(cminfo.getCmPhoto());
    model.addAttribute("cminfo", cminfo);

    return "manager/clientMgrInfoDetail";
}
//单个删除
    @RequestMapping("/deleteCminfo")
    public String deleteCminfo(int cmId, Model model){

        int nums = this.gzcrmCminfoService.deleteCminfoById(cmId);
        if (nums==1) {
            model.addAttribute("msg","<script>alert('删除客户经理成功！');</script>");
        }else {
            model.addAttribute("msg","<script>alert('删除客户经理失败！');</script>");
        }

        return "redirect:/cminfo/queryMemberList";
    }
//    修改前的查询
    @RequestMapping("/beforeUpdate")
    public String beforeUpdate(int cmId,Model model){

        GzcrmCminfo cminfo = this.gzcrmCminfoService.queryCminfoById(cmId);
        model.addAttribute("cminfo",cminfo);

        return "manager/clientMgrUpdate";
    }
//    修改
    @RequestMapping("/updateCminfo")
    public String updateCminfo(GzcrmCminfo gzcrmCminfo,Model model,MultipartFile picture){

        if (!picture.isEmpty()&&picture!=null) {
            // 保存图片的路径，图片上传成功后，将路径保存到数据库
            String filePath = "D:\\IDEA\\cms\\src\\main\\webapp\\upload\\cminfoPic";
            // 获取原始图片的扩展名
            String originalFilename = picture.getOriginalFilename();

            // 生成文件新的名字
            String newFileName = GeneratorIDUntils.getDateTime() +"."+ originalFilename.split("\\.")[1];
            // 封装上传文件位置的全路径
            File targetFile = new File(filePath, newFileName);
            try {
                picture.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 保存路径到gzcrmCminfo
            gzcrmCminfo.setCmPhoto(newFileName);
        }


        int nums = this.gzcrmCminfoService.updateCminfo(gzcrmCminfo);
        if (nums==1) {
            model.addAttribute("msg","<script>alert('更新客户经理信息成功！');</script>");
        }else {
            model.addAttribute("msg","<script>alert('更新客户经理信息失败！');</script>");
        }

        return "redirect:/cminfo/queryMemberList";
    }
//批量删除
@RequestMapping("/deleteBatch")
public String deleteMany(String checkedNum,Model model){
    boolean flag = this.gzcrmCminfoService.deleteBatch(checkedNum);
    if (flag) {
        model.addAttribute("msg","<script>alert('批量删除客户经理成功');</script>");
    }else {
        model.addAttribute("msg","<script>alert('批量删除客户经理失败');</script>");
    }

    return "redirect:/cminfo/queryMemberList";
}
//查询客户经理表
@RequestMapping("/queryCminfoStatList")
public String queryCminfoStatList(@RequestParam(required = false) String cmUnit,
                                  @RequestParam(required = false) String cmStatus,
                                  @RequestParam(required = false) String cmSex,
                                  @RequestParam(required = false) String cmEdu,
                                  @RequestParam(required = false) String cmPL,
                                  @RequestParam(required = false) String cmLevel,
                                  Model model){

    List<GzcrmCminfo> list = gzcrmCminfoService.queryDetil(cmUnit,cmStatus,cmSex,cmEdu,cmPL,cmLevel);
    model.addAttribute("cmUnit",cmUnit);
    model.addAttribute("cmStatus",cmStatus);
    model.addAttribute("cmSex",cmSex);
    model.addAttribute("cmEdu",cmEdu);
    model.addAttribute("cmPL",cmPL);
    model.addAttribute("cmLevel",cmLevel);
    model.addAttribute("list",list);

    return "manager/clientMgrStat";
}
//导出客户经理表
@RequestMapping("/exportMgrStat")
public void exportMgrStat(@RequestParam(required = false) String cmUnit,
                          @RequestParam(required = false) String cmStatus,
                          @RequestParam(required = false) String cmSex,
                          @RequestParam(required = false) String cmEdu,
                          @RequestParam(required = false) String cmPL,
                          @RequestParam(required = false) String cmLevel,
                          HttpSession session,
                          HttpServletResponse response,
                          Model model){

    List<GzcrmCminfo> list = gzcrmCminfoService.queryDetil(cmUnit,cmStatus,cmSex,cmEdu,cmPL,cmLevel);
    String name = (String)session.getAttribute("uname");
    response.setCharacterEncoding("UTF-8");

    //生成excel
    XSSFWorkbook xssf = new XSSFWorkbook();
    Sheet sheet = xssf.createSheet();
    Row row = sheet.createRow(0);
    row.createCell(0).setCellValue("客户经理编号");
    row.createCell(1).setCellValue("姓名");
    row.createCell(2).setCellValue("性别");
    row.createCell(3).setCellValue("客户经理等级");
    row.createCell(4).setCellValue("机构");
    row.createCell(5).setCellValue("部门");
    row.createCell(6).setCellValue("业务条线");
    row.createCell(7).setCellValue("职务");
    row.createCell(8).setCellValue("客户经理从业年限");
    row.createCell(9).setCellValue("联系电话");
    row.createCell(10).setCellValue("在职状态");
    row.createCell(11).setCellValue("年龄");
    row.createCell(12).setCellValue("学历");
    row.createCell(13).setCellValue("专业职称");


    //记录行数
    int index = 1;
    for (int i = 0; i < list.size(); i++) {
        GzcrmCminfo tmp = list.get(i);
        Row tmpRow = sheet.createRow(i+1);
        tmpRow.createCell(0).setCellValue(tmp.getCmId());
        tmpRow.createCell(1).setCellValue(tmp.getCmName());
        tmpRow.createCell(2).setCellValue(tmp.getCmSex());
        tmpRow.createCell(3).setCellValue(tmp.getCmLevel());
        tmpRow.createCell(4).setCellValue(tmp.getCmUnit());
        tmpRow.createCell(5).setCellValue(tmp.getCmDept());
        tmpRow.createCell(6).setCellValue(tmp.getCmBusinessLines());
        tmpRow.createCell(7).setCellValue(tmp.getCmPosition());
        tmpRow.createCell(8).setCellValue(tmp.getCmWorkingYears());
        tmpRow.createCell(9).setCellValue(tmp.getCmMobile());
        tmpRow.createCell(10).setCellValue(tmp.getCmStatus());
        tmpRow.createCell(11).setCellValue(tmp.getCmAge());
        tmpRow.createCell(12).setCellValue(tmp.getCmEducation());
        tmpRow.createCell(13).setCellValue(tmp.getCmProfessionalTitles());

    }
    System.out.println("生成成功!");
    try {
        String date = "_"+DateUtils.getSysDate().replaceAll("-","");
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="
                + new String("客户经理报表_".getBytes(),"iso-8859-1")+name+".xlsx");
        OutputStream ouputStream = response.getOutputStream();
        xssf.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

}
}
