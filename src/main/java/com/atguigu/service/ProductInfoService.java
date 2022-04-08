package com.atguigu.service;

import com.atguigu.pojo.ProductInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface ProductInfoService {

    //显示全部数据不分页
    List<ProductInfo> getAll();

    //分页功能实现
    PageInfo splitPage(int pageNum,int pageSize);

    //增加商品
    int save(ProductInfo productInfo);

    //按主键id查询商品
    ProductInfo getByID(int pid);

    int update(ProductInfo productInfo);


}
