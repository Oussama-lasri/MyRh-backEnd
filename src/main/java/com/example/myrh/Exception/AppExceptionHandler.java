package com.example.myrh.Exception;

import com.example.myrh.Utiles.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handlerInvalidException(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap) ;
    }

    @ExceptionHandler(value = {RecruteurException.class })
    public ResponseEntity<Object> handlerMemberException(Exception ex , WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {OffreException.class })
    public ResponseEntity<Object> handlerOffreException(Exception ex , WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {SoumissionOffreException.class })
    public ResponseEntity<Object> handlerSoumissionOffreException(Exception ex , WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
