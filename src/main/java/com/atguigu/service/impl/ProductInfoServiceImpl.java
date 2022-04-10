package com.atguigu.service.impl;

import com.atguigu.mapper.ProductInfoMapper;
import com.atguigu.pojo.ProductInfo;
import com.atguigu.pojo.ProductInfoExample;
import com.atguigu.pojo.vo.ProductInfoVO;
import com.atguigu.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    //切记: 业务逻辑层中一定有数据访问层的对象
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        //分页插件使用PageHelper工具类完成分页设置
        PageHelper.startPage(pageNum,pageSize);

        //进行PageInfo的数据封装
        //进行有条件的查询操作,必须要创建ProductInfoExample对象
        ProductInfoExample productInfoExample=new ProductInfoExample();
        //设置排序，按主键降序排序
        //select * from product_info order by p_id desc
        productInfoExample.setOrderByClause("p_id desc");
        //设置完排序后,取集合,切记切记:一定在取集合之前,设置PageHelper.startPage(pageNum,pageSize);
        List<ProductInfo>infoList=productInfoMapper.selectByExample(productInfoExample);
        //将查询到的集合封装进PageInfo对象中
        PageInfo<ProductInfo>pageInfo=new PageInfo<>(infoList);
        return pageInfo;
    }

    @Override
    public int save(ProductInfo productInfo) {
        return productInfoMapper.insert(productInfo);
    }

    @Override
    public ProductInfo getByID(int pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(ProductInfo productInfo) {
        return productInfoMapper.updateByPrimaryKey(productInfo);
    }

    @Override
    public int deleteByID(int pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return productInfoMapper.deleteBatch(ids);
    }

    @Override
    public List<ProductInfo> selectCondition(ProductInfoVO vo) {
        return productInfoMapper.selectCondition(vo);
    }


}
