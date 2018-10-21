package com.pinyougou.mapper;

import com.pinyougou.pojo.TbSpecification;

import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.vo.Specification;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SpecificationMapper extends Mapper<TbSpecification> {
    public Specification findById(Long id);

    public List<Map<String,String>> selectOptionList();
}
