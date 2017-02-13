package com.team11.mutualfund.controller;

import com.team11.mutualfund.response.BasicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.team11.mutualfund.utils.Constant.ILLEGALINPUT;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BasicResponse> handle(RuntimeException e) {
        BasicResponse br = new BasicResponse(ILLEGALINPUT);
        return new ResponseEntity<BasicResponse>(br, HttpStatus.BAD_REQUEST);
    }

}
