package org.example.service;

import javafx.geometry.Pos;
import org.example.domain.Posts;
import org.example.domain.Users;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/

public interface PostsService {
    void insertPosts(Posts posts);
    
    List<Posts> getPosts(Integer userId);
}
