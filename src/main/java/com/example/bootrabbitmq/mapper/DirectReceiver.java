package com.example.bootrabbitmq.mapper;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: 聂臣飞
 * @Date: 2020/10/16/10:22
 */

@Component
@RabbitListener(queues = "Q1")  //监听的队列名称 Q1
public class DirectReceiver {

    @RabbitHandler
    public void process(Map msg){
        System.out.println("第一个消费者收到消息:"+msg.toString());
    }



}
