package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.domain.Posts;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostsMapper {
    //发布用户动态
    int insertPosts(Posts posts);
    
    //查看动态
    List<Posts> getPosts(@Param("list") List<Integer> list,@Param("lastTime") String lastTime);

    Posts selectPosts(Posts posts);
    
    List<Integer> getAllFriendsByUserId(Integer userId);
}
