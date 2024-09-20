package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Posts;
import org.example.domain.Users;
import org.example.object.AjaxResult;
import org.example.service.PostsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:2024-9-18
 * @Version 1.0
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    // 引入PostsService并注入
    private final PostsService postsService;

    @PostMapping("/insertPosts")
    public AjaxResult insertPosts(@RequestBody Posts posts){
        postsService.insertPosts(posts);
        //使用统一返回格式
        return AjaxResult.success();
    }


    @GetMapping("/getPosts")
    public AjaxResult getPosts(Integer userId){
        List<Posts> postsList = postsService.getPosts(userId);
        //使用统一返回格式
        return AjaxResult.success(postsList);
    }
    
    
}
