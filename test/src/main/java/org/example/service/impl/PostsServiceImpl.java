package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.RabbitConfig;
import org.example.domain.Posts;
import org.example.domain.Users;
import org.example.mapper.PostsMapper;
import org.example.service.PostsService;
import org.example.utils.DateUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {
    // 引入 PostsMapper 并注入
    private final PostsMapper postsMapper;
    
    // 引入消息队列,用作削峰和异步
    private final RabbitTemplate rabbitTemplate;
    
    //引入redis 缓存数据
    private final RedisTemplate redisTemplate;

    String formatLastTime;

    
    @Override
    @Transactional
    public void insertPosts(Posts posts) {
        log.info("提交到消息队列");
        //拿到前端传过来雪花ID的动态编号
        rabbitTemplate.convertAndSend(RabbitConfig.POSTS_QUEUE,posts);
    }
    
    @Override
    public List<Posts> getPosts(Integer userId) {
        
        List<Integer> list = postsMapper.getAllFriendsByUserId(userId);
        //把用户自己id加进去，一起查
        list.add(userId);
        
        //从redis中取出最后一条记录的时间
        String lastTime = (String) redisTemplate.opsForValue().get(userId);
        log.info(lastTime+"------------------");
        List<Posts> posts = postsMapper.getPosts(list,lastTime);
        //拿到最后一条记录的时间，放到redis缓存中
        if(!posts.isEmpty()) {
            formatLastTime = DateUtils.DateFormat(posts.get(posts.size() - 1).getCreatedTime());
        }
        if (formatLastTime != null) {
            redisTemplate.opsForValue().set(userId , formatLastTime);
        }
        log.info(formatLastTime);

        return posts;
        
    }

}
