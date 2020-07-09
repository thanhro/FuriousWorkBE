package com.projectfinal.project.config.responseOb;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseObjectFactory {
    public static ResponseEntity toResult(Object object, HttpStatus httpStatus){
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(object,httpHeaders,httpStatus);
    }
}
