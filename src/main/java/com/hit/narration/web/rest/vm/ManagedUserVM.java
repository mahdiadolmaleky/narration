package com.hit.narration.web.rest.vm;

import com.hit.narration.service.dto.UserDTO;

import javax.validation.constraints.Size;

public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;
}
