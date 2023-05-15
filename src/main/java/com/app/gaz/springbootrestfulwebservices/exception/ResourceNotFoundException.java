package com.app.gaz.springbootrestfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fielName;
    private Long fieldValue;

    public ResourceNotFoundException(String resourceName,
                                     String fielName,
                                     Long fieldValue){
        super(String.format("%s No Encontrado Con %s : '%s'", resourceName,fielName,fieldValue));
        this.resourceName=resourceName;
        this.fielName=fielName;
        this.fieldValue=fieldValue;
    }



}
