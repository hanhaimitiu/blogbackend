package com.success.blogapi.handler;


import com.success.blogapi.vo.ErrorCode;
import com.success.blogapi.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

//对加了@Controller注解的方法进行拦截处理 AOP的实现
@ControllerAdvice
public class AllExceptionHandler {
    //进行异常处理，处理Exception.class的异常
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseBody //返回json数据
    public Result dohaveexist(){
        System.out.println("数据重复异常");
        return Result.fail(ErrorCode.DATA_EXIST.getCode(), ErrorCode.DATA_EXIST.getMsg());
    }

}
