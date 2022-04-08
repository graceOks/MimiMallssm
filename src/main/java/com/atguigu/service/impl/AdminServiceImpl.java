package com.atguigu.service.impl;

import com.atguigu.mapper.AdminMapper;
import com.atguigu.pojo.Admin;
import com.atguigu.pojo.AdminExample;
import com.atguigu.service.AdminService;
import com.atguigu.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    //在业务逻辑层中，一定会有数据访问层的对象
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String name, String password) {
        //根据传入的用户名到数据库中查询相对应的用户对象

        //如果有条件,则一定要创建AdminExample的对象,用来封装条件
        AdminExample adminExample=new AdminExample();
        //如何添加条件
        /**
         * select * from admin where a_name='admin'
         */
        //添加用户名 a_name 条件
        adminExample.createCriteria().andANameEqualTo(name);

        List<Admin> list=adminMapper.selectByExample(adminExample);
        if (list.size()>0){
            Admin admin=list.get(0);
            //如果查询到用户对象，再进行密码比对，注意密码是密文
            /**
             * 分析
             * admin.getApass==>c984aed014aec7623054f0591da07a85fd4b762d
             * pwd==>000000
             * 在进行密码对比时,要将传入pwd进行md5加密,再与数据库中查到的对象的密码进行对比
             *
             */
            String md5pwd = MD5Util.getMD5(password);
            if (md5pwd.equals(admin.getaPass())){
                return admin;
            }
        }

        return null;
    }
}
