package org.example.mapper;

import org.example.domain.Posts;
import org.example.domain.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostsMapper {
    //发布用户动态
    int insertPosts(Posts posts);
    
    //查看动态
    List<Posts> getPosts(Users users);
    
}
