package com.deepblue777.frame.handler;

import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse exceptionHandler(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException exception = (BusinessException) e;
            LOGGER.info("#### 业务-提示错误：{}", e.getMessage());
            return new BaseResponse(exception.getCode(), e.getMessage());
        }
        LOGGER.info("#### 系统-未知错误：{}", e.getMessage());
        return new BaseResponse(500, "未知错误");
    }
}
