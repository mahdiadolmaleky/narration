package com.hit.narration.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hit.narration.domain.enumeration.AgeRangeEnum;
import com.hit.narration.domain.enumeration.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nr_narration_project")
public class NarrationProject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum genderEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "sound_age")
    private AgeRangeEnum soundAge;

    @Column(name = "order_date")
    private Instant orderDate = Instant.now();

    @Column(name = "delivery_date")
    private Instant deliveryDate;

    @Column(name = "urgent")
    private Boolean urgent;

    @Column(name = "deadline")
    private Integer deadline;

    @Column(name = "word_count")
    private Integer wordCount;

    @Column(name = "speaking_time")
    private Double speakingTime;

    @Column(name = "project_cost")
    private Long projectCost;

    @ManyToOne
    @JsonIgnoreProperties(value = "narrationProjects", allowSetters = true)
    private Tun tun;

    @ManyToOne
    @JsonIgnoreProperties(value = "narrationProjects", allowSetters = true)
    private Accent accent;

    @ManyToOne
    @JsonIgnoreProperties(value = "narrationProjects", allowSetters = true)
    private Purpose purpose;

    @ManyToOne
    @JsonIgnoreProperties(value = "narrationProjects", allowSetters = true)
    private Narrator narrator;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(unique = true)
    private Attachment file;

}
