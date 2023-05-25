package com.app.gaz.springbootrestfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlredyExistsException extends Throwable {

    private String message;

    public EmailAlredyExistsException(String message){
        super();
    }

}
