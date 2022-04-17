package com.hit.narration.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hit.narration.domain.enumeration.AgeRangeEnum;
import com.hit.narration.domain.enumeration.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nr_narrator")
public class Narrator implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "narrator_code")
    private Long narratorCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum genderEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "sound_age")
    private AgeRangeEnum soundAge;

    @Column(name = "narrator_level")
    private Integer narratorLevel;

    @Column(name = "degree_id")
    private Long degreeId;

    @Column(name = "study_field_id")
    private Long studyFieldId;

    @Column(name = "start_performing")
    private Instant startPerforming;

    @Column(name = "trained")
    private Boolean trained;

    @Column(name = "lip_sync_ability")
    private Boolean lipSyncAbility;

    @Column(name = "time_sync_ability")
    private Boolean timeSyncAbility;

    @Column(name = "have_studio")
    private Boolean haveStudio;

    @Column(name = "can_travel")
    private Boolean canTravel;

    @Column(name = "daily_activity")
    private Long dailyActivity;

    @Column(name = "word_fee")
    private Long wordFee;

    @Column(name = "base_fee")
    private Long baseFee;

    @Column(name = "negotiating")
    private Boolean negotiating;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "work_experiences")
    private String workExperiences;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JsonIgnoreProperties(value = "narrators", allowSetters = true)
    private City city;

    @JsonIgnore
    @OneToMany(mappedBy = "narrator", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<SocialMedia> socialMedias = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "nr_narrator_accent",
            joinColumns = @JoinColumn(name = "narrator_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "accent_id", referencedColumnName = "id"))
    private Set<Accent> accents = new HashSet<>();

    @OneToOne
    @JoinColumn(unique = true)
    private Person person;

}
