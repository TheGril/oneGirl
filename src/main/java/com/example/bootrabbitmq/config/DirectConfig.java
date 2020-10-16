package com.example.bootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: 聂臣飞
 * @Date: 2020/10/16/9:47
 */

@Configuration
public class DirectConfig {

    //队列起名Q1
    @Bean
    public Queue Q1(){
        //一般设置队列的持久化就行，其余两个默认false
        return new Queue("Q1",true);
    }

    //交换机起名DirectE
    @Bean
    DirectExchange DirectE(){
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除
        return new DirectExchange("DirectE",true,false);
    }

    //绑定，将对列和交换机绑定，并设置用于匹配键：DirectR
    @Bean
    Binding DirectR(){
        return BindingBuilder.bind(Q1()).to(DirectE()).with("DirectR");
    }

    @Bean
    DirectExchange lonelyDirectExchange(){
        return new DirectExchange("lonelyDirectExchange");
    }
}
