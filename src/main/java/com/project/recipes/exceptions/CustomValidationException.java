package com.project.recipes.exceptions;

public class CustomValidationException extends RuntimeException{
    public CustomValidationException(String menssage){
        super(menssage);
    }
}
