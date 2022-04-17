package com.hit.narration.service.dto;

import com.hit.narration.domain.enumeration.AgeRangeEnum;
import com.hit.narration.domain.enumeration.GenderEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class VoiceDTO implements Serializable {

    private Long id;

    private String title;

    private GenderEnum genderEnum;

    private AgeRangeEnum soundAge;

    private Boolean edited;

    private Boolean mixed;

    private Integer score;

    private Long tunId;

    private Long accentId;

    private Long purposeId;

    private Long audioFileId;
    private AttachmentDTO audioFile;

    private Long narratorId;

}
