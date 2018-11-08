package cn.itcast.activemq.spring.producer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class TopicProducer {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext
                ("applicationContext-topic-consumer.xml"
                        ,"applicationContext-topic-producer.xml","applicationContext-topic-consumer1.xml");
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        Destination activeMQQueue = (Destination) context.getBean("activeTopic");
        jmsTemplate.send(activeMQQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("这是一个发布订阅消息");
                return textMessage;
            }
        });
    }
}
