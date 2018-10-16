package com.itheima.controller;

import com.itcast.pojo.Product;
import com.itcast.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    public List<Product> findAll(){
        List<Product> all = productService.findAll();
        return all;
    }
    @RequestMapping("/findByName")
    public List<Product> findByName(@RequestBody Product product ){
        List<Product> all=new ArrayList<>();
        Product byName = productService.findByName(product);
        all.add(byName);
        return all;
    }

}
