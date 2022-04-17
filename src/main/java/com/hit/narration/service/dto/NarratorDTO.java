package com.hit.narration.service.dto;

import com.hit.narration.domain.enumeration.AgeRangeEnum;
import com.hit.narration.domain.enumeration.GenderEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
public class NarratorDTO implements Serializable {

    private Long id;

    private Long narratorCode;

    private GenderEnum genderEnum;

    private AgeRangeEnum soundAge;

    private Integer narratorLevel;

    private Long degreeId;

    private Long studyFieldId;

    private Boolean trained;

    private Instant startPerforming;

    private Boolean lipSyncAbility;

    private Boolean timeSyncAbility;

    private Boolean haveStudio;

    private Boolean canTravel;

    private Long dailyActivity;

    private Long wordFee;

    private Long baseFee;

    private Boolean negotiating;

    private String cardNumber;

    private String workExperiences;

    private Boolean isActive;

    private Long cityId;

    private Set<SocialMediaDTO> socialMedias;

    private Set<AccentDTO> accents;

    private Long personId;
}
