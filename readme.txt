搭建SSM项目的步骤
1)新建Maven工程
2)修改目录,修改pom.xml文件
3)添加SSM框架的所有依赖
4)拷贝jdbc.porperties到resources目录下
5)新建ApplicationContext_dao.xml文件,进行数据访问层的配置
6)新建ApplicationContext_service.xml文件,进行业务逻辑层的配置
7)新建Springmvc.xml文件,配置springmvc的框架
8)新建SqlMapConfig.xml文件
9)使用逆向工程生成pojo和mapper的文件
10)开发业务逻辑层,实现登录判断
11)开发控制器AdminAction,完成登录处理
12)改造页面,发送登录请求,验证登录



<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">