package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.Posts;
import org.example.domain.Users;
import org.example.mapper.PostsMapper;
import org.example.service.PostsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {
    // 引入 PostsMapper 并注入
    private final PostsMapper postsMapper;

    //使用编程式事务声明，保证数据一致性
    @Transactional
    @Override
    public int insertPosts(Posts posts) {
        return postsMapper.insertPosts(posts);
    }

    @Override
    public List<Posts> getPosts(Users users) {
        return postsMapper.getPosts(users);
    }
}
