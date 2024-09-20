package org.example.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posts implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 动态编号
     */
    private Integer postId;
    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 动态内容
     */
    private String content;
    
    /**
     * 发布的图片
     */
    private String picture;

    /**
     * 发布的位置
     */
    private String location;

    /**
     *动态创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createdTime;

    
}
