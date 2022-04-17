package com.hit.narration.service.dto;

import com.hit.narration.config.Constants;

import com.hit.narration.domain.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDTO implements Serializable {

    private Long id;

    @Email
    private String email;

    @Column(length = 11)
    @Size(min = 11, max = 11)
    @Pattern(regexp = Constants.PHONE_NUMBER_REGEX)
    private String phoneNumber;

    @NotNull
    private String password;

    @NotNull
    @Size(max = 50)
    private String firstName;

    @NotNull
    @Size(max = 50)
    private String lastName;

    private Instant createdDate;

    private Instant lastModifiedDate;

    private Instant lastLogin;

    private Boolean enabled;

    private Boolean emailConfirmed;

    private String token;

    private Instant tokenCreatedDate;

    private Boolean tokenExpired;

    private Set<RoleDTO> roles ;

}
