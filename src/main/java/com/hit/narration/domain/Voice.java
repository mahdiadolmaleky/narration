package com.hit.narration.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hit.narration.domain.enumeration.AgeRangeEnum;
import com.hit.narration.domain.enumeration.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nr_voice")
public class Voice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum genderEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "sound_age")
    private AgeRangeEnum soundAge;

    @Column(name = "edited")
    private Boolean edited;

    @Column(name = "mixed")
    private Boolean mixed;

    @Column(name = "score")
    private Integer score;

    @ManyToOne
    @JsonIgnoreProperties(value = "voices", allowSetters = true)
    private Tun tun;

    @ManyToOne
    @JsonIgnoreProperties(value = "voices", allowSetters = true)
    private Accent accent;

    @ManyToOne
    @JsonIgnoreProperties(value = "voices", allowSetters = true)
    private Purpose purpose;

    @ManyToOne
    @JsonIgnoreProperties(value = "voices", allowSetters = true)
    private Attachment audioFile;

    @ManyToOne
    @JsonIgnoreProperties(value = "voices", allowSetters = true)
    private Narrator narrator;

}
