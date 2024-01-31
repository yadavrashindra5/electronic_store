package com.lcwd.electronic.store.exceptions;

import com.lcwd.electronic.store.dtos.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //handle resource not found exception

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundException(ResourceNotFoundException e){
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message(e.getMessage())
                .success(false)
                .status(HttpStatus.NOT_FOUND)
                .build();
        logger.info("Exception Handler invoked");
        ResponseEntity<ApiResponseMessage>response=new ResponseEntity<>(responseMessage,HttpStatus.NOT_FOUND);
        return response;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ObjectError>allErrors=e.getBindingResult().getAllErrors();
        Map<String,Object>response=new HashMap<>();
        allErrors.stream().forEach(objectError -> {
            String message=objectError.getDefaultMessage();
            String field = ((FieldError) objectError).getField();
            response.put(field,message);
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadApiRequestException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundException(BadApiRequestException e){
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message(e.getMessage())
                .success(false)
                .status(HttpStatus.NOT_FOUND)
                .build();
        ResponseEntity<ApiResponseMessage>response=new ResponseEntity<>(responseMessage,HttpStatus.NOT_FOUND);
        return response;
    }
}
