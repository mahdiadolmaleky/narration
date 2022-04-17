package com.hit.narration.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LanguageDTO implements Serializable {
    
    private Long id;

    private String title;

    private Long coefficient;

}
