package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import com.pinyougou.service.BaseService;
import com.pinyougou.vo.PageResult;

import java.util.List;
import java.util.Map;

public interface BrandService extends BaseService<TbBrand> {
    public List<TbBrand> queryAll();

    PageResult search(TbBrand brand, Integer pageNo, Integer rows);

    //批量删除
    public void batchDelete(Long[] ids);

    /**
     * 查询品牌列表
     *
     * @return
     */
    List<Map<String, String>> selectOptionList();
}
