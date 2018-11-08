package com.pinyougou.search.queue;

import com.alibaba.fastjson.JSONArray;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.adapter.AbstractAdaptableMessageListener;

import javax.jms.*;
import java.io.Serializable;
import java.util.List;

public class ItemDeleteSolrActiveListener extends AbstractAdaptableMessageListener {
    @Autowired
    private ItemSearchService itemSearchService;
    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        ObjectMessage objectMessage = (ObjectMessage) message;
        Long[]ids= (Long[]) objectMessage.getObject();
        //删除索引库
        itemSearchService.deleteList(ids);
        System.out.println("删除sku同步索引库数据完成。");
    }
}
