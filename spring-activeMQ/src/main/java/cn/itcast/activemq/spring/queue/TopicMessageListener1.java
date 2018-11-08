package cn.itcast.activemq.spring.queue;

import org.springframework.jms.listener.adapter.AbstractAdaptableMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class TopicMessageListener1 extends AbstractAdaptableMessageListener {
    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        if (message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("发布订阅监听器1111");
            System.out.println(textMessage.getText());
        }
    }
}
