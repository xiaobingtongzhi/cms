package com.chenbing.inspur.controller;

import com.chenbing.inspur.pojo.YcMember;
import com.chenbing.inspur.service.MemberService;
import com.chenbing.inspur.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Juny
 * @date 2020/7/2 - 15:04
 */
@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/login")
    public String login(@RequestParam("loginName")String loginName,
                        @RequestParam("loginPass")String loginPass,
                        Model model, HttpSession session){
        List<YcMember> list = memberService.login(loginName,loginPass);
        if (list.size()==1){
            session.setAttribute("uname",loginName);
            session.setAttribute("uid",list.get(0).getMemId());
            session.setAttribute("urole",list.get(0).getRoleid());
            return "main";/*登录成功*/
        }else{
            model.addAttribute("msg","登录失败");
            return "login";
        }
    }
}
