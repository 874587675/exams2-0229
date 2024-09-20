package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.object.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({SQLException.class})
    public AjaxResult handler(SQLException exception) {
        log.error(String.valueOf(exception));
        return AjaxResult.error("数据库SQL异常");
    }
    
    @ExceptionHandler({ArrayIndexOutOfBoundsException.class})
    public AjaxResult handler(ArrayIndexOutOfBoundsException exception) {
        log.error(String.valueOf(exception));
        return AjaxResult.error("数组越界,后面已经没有数据了");
    }
    
}
