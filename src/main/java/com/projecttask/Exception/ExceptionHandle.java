package com.projecttask.Exception;

import com.projecttask.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ErrorDetails> ResourceNotFoundExceptionHandler(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails details=new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorDetails> GlobalExceptionHandler(Exception exception, WebRequest webRequest){
        ErrorDetails details=new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }
}
