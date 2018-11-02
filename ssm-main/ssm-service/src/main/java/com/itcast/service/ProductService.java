package com.pinyougou.service;

import com.pinyougou.pojo.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findByName(Product product);
}
