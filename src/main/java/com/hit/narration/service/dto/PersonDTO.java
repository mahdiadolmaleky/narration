package com.hit.narration.service.dto;

import com.hit.narration.domain.enumeration.SexEnum;
import com.hit.narration.validation.UniqueNationalCode;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Data
@UniqueNationalCode(message = "national code is not valid")
public class PersonDTO implements Serializable {

    private Long id;

    private SexEnum sex;

    @NotNull
    @Size(min = 10, max = 10)
    @Column(name = "national_code", length = 10, nullable = false, unique = true)
    private String nationalCode;

    private Instant birthDate;

    private String address;

    private String about;

    private Long userId;

}
