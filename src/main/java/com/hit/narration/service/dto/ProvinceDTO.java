package com.hit.narration.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProvinceDTO implements Serializable {
    
    private Long id;

    private String title;

    private Long countryId;
}
