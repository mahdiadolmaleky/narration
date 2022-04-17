package com.hit.narration.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountryDTO implements Serializable {
    
    private Long id;

    private String title;

    private String capital;

    private String callingCode;

}
