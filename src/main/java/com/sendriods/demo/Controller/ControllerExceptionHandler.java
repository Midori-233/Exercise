package com.sendriods.demo.Controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String handleException1(Exception e) {
        String msg = "Exception:" + e.getMessage();
        System.out.println(msg);
        return msg;
    }
}
