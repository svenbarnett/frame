package com.deepblue777.frame.handler;

import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @description: 全局异常处理类
 * @author: HYY
 * @date: 2019/9/6
 * @since
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public BaseResponse exceptionHandler(Exception e) {
    if (e instanceof BusinessException) {
      BusinessException exception = (BusinessException) e;
      return new BaseResponse(exception.getCode(), e.getMessage());
    }

    return new BaseResponse(500, "未知错误");
  }
}
