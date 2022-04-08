package com.atguigu.mapper;

import com.atguigu.pojo.Carshop;
import com.atguigu.pojo.CarshopExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarshopMapper {
    long countByExample(CarshopExample example);

    int deleteByExample(CarshopExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Carshop record);

    int insertSelective(Carshop record);

    List<Carshop> selectByExample(CarshopExample example);

    Carshop selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Carshop record, @Param("example") CarshopExample example);

    int updateByExample(@Param("record") Carshop record, @Param("example") CarshopExample example);

    int updateByPrimaryKeySelective(Carshop record);

    int updateByPrimaryKey(Carshop record);
}