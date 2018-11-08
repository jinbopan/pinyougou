package com.pinyougou.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.sellergoods.service.GoodsService;
import com.pinyougou.sellergoods.service.ItemCatService;
import com.pinyougou.vo.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.Map;

@Controller
public class ItemFreemarkerController {
    @Reference
    private GoodsService goodsService;

    @Reference
    private ItemCatService itemCatService;

    @RequestMapping("/{goodsId}")
    public ModelAndView toItemDetails(@PathVariable("goodsId")Long goodsId){
        ModelAndView mv=new ModelAndView("item");
        //根据商品ID查询有效的商品
        Goods goods = goodsService.findByGoodsIdAndStatus(goodsId, "1");
        mv.addObject("goodsDesc", goods.getGoodsDesc());
        mv.addObject("itemList", goods.getItemList());
        mv.addObject("goods", goods.getGoods());

        TbItemCat itemCat1 = itemCatService.findById(goods.getGoods().getCategory1Id());
        mv.addObject("itemCat1",itemCat1.getName() );
        TbItemCat itemCat2 = itemCatService.findById(goods.getGoods().getCategory2Id());
        mv.addObject("itemCat2", itemCat2.getName());
        TbItemCat itemCat3 = itemCatService.findById(goods.getGoods().getCategory3Id());
        mv.addObject("itemCat3", itemCat3.getName());

        return mv;
    }
}
