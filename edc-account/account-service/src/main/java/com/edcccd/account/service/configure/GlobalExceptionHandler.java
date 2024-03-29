package com.edcccd.account.service.configure;

import com.edcccd.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常全局处理器
 */
@ControllerAdvice
@ResponseBody // 返回值为json串，写到body中
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 数据校验全局处理
     * BindException是@Valid使用校验失败时产生的异常
     */
    @ExceptionHandler(value = BindException.class)
    public Result<String> BindExceptionHandler(BindException e) {
        //获取实体类定义的校验注解字段上的message作为异常信息
        String messageInfo = e.getBindingResult().getFieldError() == null ?
                "输入参数错误" : e.getBindingResult().getFieldError().getDefaultMessage();

        return Result.fail(500, messageInfo);
    }

    /**
     * 全局异常处理
     * <p>
     * 设置处理的异常类型，如果没有特定异常则为最大的异常Exception.class
     * </p>
     */
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        return Result.fail(100, e.getMessage());
    }
}

