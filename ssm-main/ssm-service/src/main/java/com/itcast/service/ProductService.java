package com.itcast.service;

import com.itcast.pojo.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findByName(Product product);
}
