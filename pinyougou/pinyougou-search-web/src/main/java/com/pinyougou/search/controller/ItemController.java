package com.pinyougou.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.search.service.ItemSearchService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class ItemController {

    @Reference
    private ItemSearchService itemSearchService;

    @PostMapping("/itemSearch")
    public Map<String,Object> itemSearch(@RequestBody Map<String,Object> searchMap){
        Map<String, Object> all = itemSearchService.findAll(searchMap);
        return all;
    }
}
