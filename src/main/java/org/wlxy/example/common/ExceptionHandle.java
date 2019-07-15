package org.wlxy.example.common;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        e.printStackTrace(); //把错误信息打印出来
        if(e instanceof MyException){
            return MyRsp.wrapper((MyException)e);
        }else if(e instanceof ArithmeticException){
            return MyRsp.error().msg("这是一个算数异常");
        }else if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException ex= (MethodArgumentNotValidException)e;
            return MyRsp.error().msg(ex.getBindingResult()
                    .getFieldError().getDefaultMessage());
        }else if(e instanceof HttpRequestMethodNotSupportedException){
            HttpRequestMethodNotSupportedException ex= (HttpRequestMethodNotSupportedException)e;
            return MyRsp.error().msg(ex.getMessage());
        }else if(e instanceof HttpMessageNotReadableException){
            HttpMessageNotReadableException ex= (HttpMessageNotReadableException)e;
            return MyRsp.error().msg("json数据格式可能发生错误，请检查："+ex.getMessage());
        }else {
            return MyRsp.error().msg("这是一个未知异常");
        }

    }
}
