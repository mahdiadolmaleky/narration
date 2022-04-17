package com.hit.narration.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegionDTO implements Serializable {
    
    private Long id;

    private Long parentId;

    private String title;
}
