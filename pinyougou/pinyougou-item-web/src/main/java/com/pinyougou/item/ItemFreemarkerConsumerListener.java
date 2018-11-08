package com.pinyougou.item;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.sellergoods.service.GoodsService;
import com.pinyougou.sellergoods.service.ItemCatService;
import com.pinyougou.vo.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.listener.adapter.AbstractAdaptableMessageListener;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ItemFreemarkerConsumerListener extends AbstractAdaptableMessageListener {
    @Reference
    private GoodsService goodsService;
    @Reference
    private ItemCatService itemCatService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${file_path}")
    private String file_path;
    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        ObjectMessage objectMessage = (ObjectMessage) message;
        Long[] goodsIds= (Long[]) objectMessage.getObject();
        if (goodsIds != null && goodsIds.length > 0) {
            for (Long goodsId : goodsIds) {
                genItemHtml(goodsId);
            }
        }
    }

    private void genItemHtml(Long goodsId)  {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = null;
        try {
            template = configuration.getTemplate("item.ftl");
            Map<String,Object> dataModel = new HashMap();
            //根据商品ID查询有效的商品
            Goods goods = goodsService.findByGoodsIdAndStatus(goodsId, "1");
            dataModel.put("goodsDesc", goods.getGoodsDesc());
            dataModel.put("itemList", goods.getItemList());
            dataModel.put("goods", goods.getGoods());

            TbItemCat itemCat1 = itemCatService.findById(goods.getGoods().getCategory1Id());
            dataModel.put("itemCat1", itemCat1.getName());
            TbItemCat itemCat2 = itemCatService.findById(goods.getGoods().getCategory2Id());
            dataModel.put("itemCat2", itemCat2.getName());
            TbItemCat itemCat3 = itemCatService.findById(goods.getGoods().getCategory3Id());
            dataModel.put("itemCat3", itemCat3.getName());

            Writer out = new FileWriter(new File(file_path + goodsId + ".html"));
            template.process(dataModel, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
