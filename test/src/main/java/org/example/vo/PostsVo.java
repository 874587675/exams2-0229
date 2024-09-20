package org.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.Posts;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsVo extends Posts {
    private String userName;
}
