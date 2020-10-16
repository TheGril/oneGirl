package com.example.bootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: 聂臣飞
 * @Date: 2020/10/16/10:45
 */

@Configuration
public class TopicConfig {

    //绑定键
    public final static String man = "topic.man";
    public final static String woman = "topic.woman";

    @Bean
    public Queue fQueue(){
        return new Queue(TopicConfig.man);
    }

    @Bean
    public Queue tQueue(){
        return new Queue(TopicConfig.woman);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("topicE");

    }

    //将fQueue exchange 绑定，而且绑定的键值为topic.man
    //只要是消息携带的路由键是topic.man，才会分发到该队列
    @Bean
    Binding bindingEMsg(){
        return BindingBuilder.bind(fQueue()).to(exchange()).with("topic.*");
    }

    //将tQueue exchange 绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(tQueue()).to(exchange()).with("topic.#");
    }




}
