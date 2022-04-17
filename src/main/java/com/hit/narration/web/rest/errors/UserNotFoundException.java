package com.hit.narration.web.rest.errors;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("user not found!");
    }

}
