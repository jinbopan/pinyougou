package com.pinyougou.item;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.listener.adapter.AbstractAdaptableMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.File;

public class ItemDeleteFreemarkerConsumerListener extends AbstractAdaptableMessageListener {

    @Value("${file_path}")
    private String file_path;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        ObjectMessage objectMessage = (ObjectMessage) message;
        Long[] goodsIds = (Long[]) objectMessage.getObject();
        if (goodsIds != null && goodsIds.length > 0) {
            for (int i = 0; i < goodsIds.length; i++) {
                String fileName = file_path + goodsIds[i] + ".html";
                File file = new File(fileName);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

}
