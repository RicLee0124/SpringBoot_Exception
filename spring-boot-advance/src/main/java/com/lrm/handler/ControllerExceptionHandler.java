package com.lrm.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    private  final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);


    //全局统一异常处理
    @ExceptionHandler({Exception.class})
//    @ResponseStatus
    public ModelAndView handleException(HttpServletRequest request, Exception e) throws Exception {
        logger.error("Request Url : {}, Exception : {}", request.getRequestURI(),e.getMessage());
        //使用注解工具，查看有没有自定义异常和状态码注解，有的话，抛出该异常
        if(AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class)!=null){
            throw e;
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("url",request.getRequestURI());
        mav.addObject("exception",e);
        mav.setViewName("error/error");

        return mav;
    }
}
