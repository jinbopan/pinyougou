package cn.itcast.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sms")
public class MQController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    /**
     *  发送一个 map 消息到 MQ 的队列
     * @return
     */
    @GetMapping("/send")
    public String sendMapMsg(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", 123L);
        map.put("name", " 传智播客");
        jmsMessagingTemplate.convertAndSend("spring.boot.map.queue", map);
        return " 发送消息完成。";
    }

    @GetMapping("/sendMap")
    public String sendMapMsgs(){
        Map<String, String> map = new HashMap<>();
        map.put("mobile", "13556784623");
        map.put("signName", "品优购");
        map.put("templateCode", "SMS_147970638");
        map.put("templateParam", "{\"name\":\"123456\"}");
        jmsMessagingTemplate.convertAndSend("itcast_sms_queue", map);
        return " 发送 sms  消息完成。";
    }


}
