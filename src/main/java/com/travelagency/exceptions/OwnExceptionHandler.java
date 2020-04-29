package com.travelagency.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class OwnExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView catchResourceNotFoundException(String message) {
        ModelAndView model = new ModelAndView("main/exception_page");
        model.addObject("message", message);
        return model;
    }
}
