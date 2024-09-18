package org.example.controller;

import com.lititi.exams.commons2.object.CommonResultObject;
import lombok.RequiredArgsConstructor;
import org.example.domain.Posts;
import org.example.domain.Users;
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
    
    // 引入 PostsService 并注入
    private final PostsService postsService;
    
    
    @PostMapping("/insertPosts")
    public CommonResultObject insertPosts(@RequestBody Posts posts){
        //使用统一返回格式
        CommonResultObject commonResultObject = new CommonResultObject();
        int result = postsService.insertPosts(posts);
        return commonResultObject.addObject("result",result);
    }
    
    @GetMapping("/getPosts")
    public CommonResultObject getPosts(@RequestBody Users users){
        //使用统一返回格式
        CommonResultObject commonResultObject = new CommonResultObject();
        
        List<Posts> postsList = postsService.getPosts(users);
        
        return commonResultObject.addObject("postsList",postsList);
    }
}
