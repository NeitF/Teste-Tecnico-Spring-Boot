package com.app.simpleapi.exceptions;

public class UserFriendlyException extends RuntimeException {
    public UserFriendlyException(String message){
        super(message);
    }
}
