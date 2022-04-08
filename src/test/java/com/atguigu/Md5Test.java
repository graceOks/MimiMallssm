package com.atguigu;

import com.atguigu.utils.MD5Util;
import org.junit.Test;

public class Md5Test {


    @Test
    public void test1(){
        String md5 = MD5Util.getMD5("000000");
        System.out.println("md5 = " + md5);

    }
}
