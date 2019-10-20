package com.tw.apistackbase.errorExceptionHandler;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.error.ErrorMessage;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.HttpCookie;

@ControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorMessage handle(NotFoundException e){
        ErrorMessage error = new ErrorMessage();
        error.setCode(404);
        error.setMessage(e.getMessage());

        return error;
    }
}
