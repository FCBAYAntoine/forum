package com.antoine.forum.exception;


import com.antoine.forum.common.AppResult;
import com.antoine.forum.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(ApplicationException.class)
    public AppResult applicationException(ApplicationException e){
        e.printStackTrace();
        log.error(e.getMessage());
        if(e.getErrorResult()!=null){
            return e.getErrorResult();
        }
        if(e.getMessage()==null||e.getMessage().equals("")){
            return AppResult.failed(ResultCode.ERROR_SERVICES);
        }
        return AppResult.failed(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public AppResult exceptionHandler(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        if(e.getMessage()==null||e.getMessage().equals("")){
            return AppResult.failed(ResultCode.ERROR_SERVICES);
        }
        return AppResult.failed(e.getMessage());
    }
}
