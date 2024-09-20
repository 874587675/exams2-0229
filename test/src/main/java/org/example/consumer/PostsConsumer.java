package org.example.consumer;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.RabbitConfig;
import org.example.domain.Posts;
import org.example.mapper.PostsMapper;
import org.example.utils.DateUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class PostsConsumer implements ChannelAwareMessageListener {
    
    private final PostsMapper postsMapper;
    @Override
    @Transactional
    @RabbitListener(queues = RabbitConfig.POSTS_QUEUE)
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            // 反序列化消息内容,字节数组（通常是从消息队列中读取的）转换回对象实例
            Posts posts = (Posts) SerializationUtils.deserialize(message.getBody());
            log.info(posts.getPostId()+"----"+posts.getUserId());
            
            // 插入前先检查动态信息,要确保幂等
            if (postsMapper.selectPosts(posts)==null){
                // 插入动态信息
                postsMapper.insertPosts(posts);
            }
            // 手动确认消息  第二个参数是否批量确认消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 处理异常，比如重试或记录日志
            log.error("消息确认失败"+ DateUtils.getCurrentDate());
            //第二个参数 false：表示是否对所有未确认的消息进行拒绝。如果设置为 false，只拒绝当前消息；如果为 true，则拒绝当前消息及之前的所有未确认消息。
            //第三个参数 true：表示消息是否重新入队。如果设置为 true，被拒绝的消息会被重新放回队列中；如果为 false，则消息将被丢弃，转发到死信队列（如果配置了）。
            //这里动态数据并不是很重要,消息丢失让用户重新发一遍,不必人工在死信队列中处理
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }
}
