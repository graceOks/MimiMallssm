package com.atguigu.service;

import com.atguigu.pojo.ProductInfo;
import com.atguigu.pojo.vo.ProductInfoVO;
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

    //修改
    int update(ProductInfo productInfo);


    //删除
    int deleteByID(int pid);

    //批量删除
    int deleteBatch(String []ids);

    /**
     * 多条件查询
     * @param vo ProductInfoVO 实体类
     * @return
     */
    List<ProductInfo> selectCondition(ProductInfoVO vo);
}
