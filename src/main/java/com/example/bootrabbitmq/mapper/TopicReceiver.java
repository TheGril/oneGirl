package com.example.bootrabbitmq.mapper;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: 聂臣飞
 * @Date: 2020/10/16/11:01
 */

@Component
@RabbitListener(queues = "topic.man")
public class TopicReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicReceiver消费者收到消息  : " + testMessage.toString());
    }
}
