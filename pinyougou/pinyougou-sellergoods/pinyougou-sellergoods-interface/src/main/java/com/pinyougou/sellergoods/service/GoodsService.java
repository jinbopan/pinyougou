package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.service.BaseService;
import com.pinyougou.vo.Goods;
import com.pinyougou.vo.PageResult;

import java.util.List;

public interface GoodsService extends BaseService<TbGoods> {

    PageResult search(Integer page, Integer rows, TbGoods goods);

    void addGoods(Goods goods);

    Goods findGoodsById(Long id);

    void update(Goods goods);

    void updateStatus(Long[] ids, String status);

    void updateIsDelete(Long[] ids);

    void updateIsMarketable(Long[] ids, String status);

    List<TbItem> searchSkuByIds(Long[] ids);

    Goods findByGoodsIdAndStatus(Long goodsId, String status);
}