package com.edcccd.account.service.configure;

import com.edcccd.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 数据校验全局处理
     * BindException是@Valid使用校验失败时产生的异常
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result<String> BindExceptionHandler(BindException e) {

        //获取实体类定义的校验注解字段上的message作为异常信息
        String messageInfo = e.getBindingResult().getFieldError() == null ?
                "输入参数错误" : e.getBindingResult().getFieldError().getDefaultMessage();

        return Result.fail(500, messageInfo);
    }

    /**
     * 全局异常处理
     * 如果设置了特定异常处理，全局异常处理可作为兜底异常
     *
     * @param e
     */
    //设置处理的异常类型，如果没有特定异常则为最大的异常Exception.class
    @ExceptionHandler(value = Exception.class)
    //最终结果是JSON格式
    @ResponseBody
    public Result<String> exceptionHandler(Exception e) {
        Result<String> resultInfo = Result.fail(100, e.getMessage());
        return resultInfo;
    }
}

