package com.example.bootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: 聂臣飞
 * @Date: 2020/10/16/10:18
 */
@Configuration
public class ConsumerConfig {

    //队列 起名：TestDirectQueue
    @Bean
    public Queue CQ1() {
        return new Queue("CQ1",true);
    }

    //交换机起名cDirectE
    @Bean
    DirectExchange cDirectE() {
        return new DirectExchange("DirectE");
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：cDirectR
    @Bean
    Binding cDirectR() {
        return BindingBuilder.bind(CQ1()).to(cDirectE()).with("cDirectR");
    }

}
