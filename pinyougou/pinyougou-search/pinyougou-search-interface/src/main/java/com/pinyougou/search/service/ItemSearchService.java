package com.pinyougou.search.service;

import com.pinyougou.pojo.TbItem;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {
    Map<String,Object>findAll(Map searchMap);

    void importList(List<TbItem> itemList);

    void deleteList(Long[] ids);
}
