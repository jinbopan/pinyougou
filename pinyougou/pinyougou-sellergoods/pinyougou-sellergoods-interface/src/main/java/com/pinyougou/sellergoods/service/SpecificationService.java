package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.service.BaseService;
import com.pinyougou.vo.PageResult;
import com.pinyougou.vo.Specification;

import java.util.List;
import java.util.Map;

public interface SpecificationService extends BaseService<TbSpecification> {

    PageResult search(Integer page, Integer rows, TbSpecification specification);

    /**
     * 添加规格选项
     * @param specification
     * @return
     */
    void add(Specification specification);
    Specification findById(Long id);

    void update(Specification specification);
    void delete(Long [] ids);
    /**
     * 查询规格选项列表
     * @return
     */
    List<Map<String,String>> selectOptionList();
}