package com.example.bootrabbitmq.controll;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: 聂臣飞
 * @Date: 2020/10/16/9:58
 */

@RestController
public class sendMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate; //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("sendMSG")
    public String sendMsg(){
        String msgID = String.valueOf(UUID.randomUUID());
        String msgData = "你好";
        String formatTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("messageId",msgID);
        map.put("messageData",msgData);
        map.put("createTime",formatTime);
        //将消息携带绑定键值：DirectR 发送到交换机DirectE
        rabbitTemplate.convertAndSend("DirectE","DirectR",map);
        return "OK";
    }

    @GetMapping("/send1")
    public String sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicE", "topic.man", manMap);
        return "ok";
    }

    @GetMapping("/send2")
    public String sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicE", "topic.woman", womanMap);
        return "ok";
    }

    @GetMapping("/sendf")
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: testFanoutMessage ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutE", null, map);
        return "ok";
    }



}
