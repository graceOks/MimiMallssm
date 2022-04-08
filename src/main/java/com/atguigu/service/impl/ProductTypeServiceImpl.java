package com.atguigu.service.impl;

import com.atguigu.mapper.ProductTypeMapper;
import com.atguigu.pojo.ProductType;
import com.atguigu.pojo.ProductTypeExample;
import com.atguigu.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeMapper   productTypeMapper;

    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
