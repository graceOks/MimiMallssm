package com.atguigu.listener;

import com.atguigu.pojo.ProductType;
import com.atguigu.service.ProductTypeService;
import com.atguigu.service.impl.ProductTypeServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ProductTypeListener implements ServletContextListener {



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //手工从Spring容器中取出ProductTypeServiceImpl的对象
        ApplicationContext context=new ClassPathXmlApplicationContext("config/applicationContext*.xml");
        ProductTypeService productTypeService= (ProductTypeService) context.getBean("productTypeServiceImpl");
        System.out.println("productTypeService = " + productTypeService);
        List<ProductType> typeList = productTypeService.getAll();

        //放入全局应用作用域中，供修改页面，前台的查询功能提供全部商品类别集合
        servletContextEvent.getServletContext().setAttribute("typeList",typeList);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
