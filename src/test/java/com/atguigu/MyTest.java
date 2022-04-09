package com.atguigu;


import com.atguigu.mapper.ProductInfoMapper;
import com.atguigu.pojo.ProductInfo;
import com.atguigu.pojo.vo.ProductInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/applicationContext_dao.xml","/config/applicationContext_service.xml"})
//@ContextConfiguration(locations = {"classpath:config/applicationContext*.xml"})
public class MyTest {

    @Autowired
    ProductInfoMapper productInfoMapper;


    @Test
    public void testSelectCondition(){
        ProductInfoVO productInfoVO = new ProductInfoVO();
        productInfoVO.setPname("a");
        productInfoVO.setTypeid(2);
        productInfoVO.setLprice(3000);
        List<ProductInfo> productInfos = productInfoMapper.selectCondition(productInfoVO);
        productInfos.forEach(productInfo -> System.out.println());
    }
}
