package com.hit.narration.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class AccentDTO implements Serializable {
    
    private Long id;

    private String title;

    private Long coefficient;

    private Long languageId;

    private Set<NarratorDTO> narrators;

}
