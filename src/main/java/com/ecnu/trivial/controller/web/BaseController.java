package com.ecnu.trivial.controller.web;

import com.ecnu.trivial.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
    private static final String USER_ID="user_id";
    @Autowired
    protected HttpServletRequest request;

    @ExceptionHandler
    public String handleException(ResourceNotFoundException ex) {
        return "404";
    }

    public int getCurrentUserID(){
        Object o=request.getSession().getAttribute(USER_ID);
        if(o==null){
            throw new ResourceNotFoundException();
        }
        return Integer.parseInt(o.toString());
    }
}
