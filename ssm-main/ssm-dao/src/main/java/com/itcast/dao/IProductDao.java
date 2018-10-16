package com.itcast.dao;

import com.itcast.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
public interface IProductDao {
    /**
     * 1.  查询全部
     */
    @Select("select * from product")
    List<Product> findAll();

    @Select("select * from product where productName like CONCAT(CONCAT('%', #{productName}),'%')")
    Product findByName(Product product);
}
