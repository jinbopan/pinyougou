package com.pinyougou.search.queue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.adapter.AbstractAdaptableMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.List;

public class ItemImportSolrActiveListener extends AbstractAdaptableMessageListener {
    @Autowired
    private ItemSearchService itemSearchService;
    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        String text = textMessage.getText();
        List<TbItem> itemList = JSONArray.parseArray(text, TbItem.class);
        //导入索引库
        itemSearchService.importList(itemList);
        System.out.println(" 同步索引库数据完成。");
    }
}
