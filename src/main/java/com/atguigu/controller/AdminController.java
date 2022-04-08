package com.atguigu.controller;
import com.atguigu.pojo.Admin;
import com.atguigu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {

    //切记:在所有的界画层,一定会有业务逻辑层的对象
    @Autowired
    AdminService adminService;


    //实现登录判断，并进行相应的跳转
    @RequestMapping("/login.action")
    public String login(String name, String pwd, HttpServletRequest request){
        System.out.println("1111");
        Admin admin=adminService.login(name, pwd);
        if (admin != null) {
            //登录成功
            request.getSession().setAttribute("admin",admin);
            Object admin1 = request.getSession().getAttribute("admin");
            System.out.println("admin1 = " + admin1);
            return "main";
        }
        else {
            //登录失败
            request.setAttribute("errmsg","用户名或密码不正确");
            Object admin1 = request.getAttribute("admin");
            System.out.println("admin1 = " + admin1);
            return "login";
        }
    }

    @RequestMapping("/logout.action")
    public String logout(HttpServletRequest request){
        System.out.println("222");

        Object attribute = request.getSession().getAttribute("admin");
        System.out.println("attribute = " + attribute);
        //
        HttpSession session = request.getSession();
        session.invalidate();
        //删除域中值
        request.getSession().removeAttribute("admin");
        return "login";
    }


}
