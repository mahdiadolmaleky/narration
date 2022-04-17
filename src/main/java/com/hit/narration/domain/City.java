package com.hit.narration.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nr_city")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "calling_code")
    private String callingCode;

    @ManyToOne
    @JsonIgnoreProperties(value = "cities", allowSetters = true)
    private Province province;

    @ManyToOne
    @JsonIgnoreProperties(value = "cities", allowSetters = true)
    private Accent accent;

    @ManyToOne
    @JsonIgnoreProperties(value = "cities", allowSetters = true)
    private Region region;
}
