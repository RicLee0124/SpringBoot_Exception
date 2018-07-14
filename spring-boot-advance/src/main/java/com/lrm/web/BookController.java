package com.lrm.web;


import com.lrm.domain.Book;
import com.lrm.exception.BookNotFoundException;
import com.lrm.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/books")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;


    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book",book);
        return  "book";
    }


    /*@ExceptionHandler({Exception.class})
    public ModelAndView handleException(HttpServletRequest request,Exception e) throws Exception {
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
    }*/

}
