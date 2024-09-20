package org.example.config;

import org.example.consumer.PostsConsumer;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@Configuration
public class RabbitConfig {
    public static final String POSTS_QUEUE = "posts_queue";
    
    @Bean
    public Queue postsQueue() {
        return new Queue(POSTS_QUEUE);
    }

    //处理RabbitMQ的消息
    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                    PostsConsumer postsConsumer) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(POSTS_QUEUE);
        container.setMessageListener(postsConsumer);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置手动确认模式
        return container;
    }
    
}
