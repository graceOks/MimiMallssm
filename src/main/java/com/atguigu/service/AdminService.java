package com.atguigu.service;

import com.atguigu.pojo.Admin;

public interface AdminService {

    //完成登录判断
    Admin login(String name, String password);
}
