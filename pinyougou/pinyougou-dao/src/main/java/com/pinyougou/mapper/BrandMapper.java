package com.pinyougou.mapper;

import com.pinyougou.pojo.TbBrand;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface BrandMapper extends Mapper<TbBrand> {
    public List<TbBrand> queryAll();

    //批量删除
    public void batchDelete(Long[] ids);

    public List<Map<String, String>> selectOptionList();
}
