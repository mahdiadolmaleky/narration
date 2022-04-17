package com.hit.narration.service.dto;

import com.hit.narration.domain.Attachment;
import com.hit.narration.domain.enumeration.AgeRangeEnum;
import com.hit.narration.domain.enumeration.GenderEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class NarrationProjectDTO implements Serializable {

    private Long id;

    private String projectName;

    private GenderEnum genderEnum;

    private AgeRangeEnum soundAge;

    private Instant orderDate;

    private Instant deliveryDate;

    private Boolean urgent;

    private Integer deadline;

    private Integer wordCount;

    private Double speakingTime;

    private Long projectCost;

    private Long tunId;

    private Long accentId;

    private Long purposeId;

    private Long narratorId;

    private Attachment file;
    private Long fileId;
}
