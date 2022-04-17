package com.hit.narration.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SocialMediaDTO implements Serializable {
    
    private Long id;

    private Long typeId;

    private String address;

    private String description;

    private Long narratorId;

}
