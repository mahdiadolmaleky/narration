package com.hit.narration.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nr_accent")
public class Accent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "coefficient")
    private Long coefficient;

    @ManyToOne
    @JsonIgnoreProperties(value = "accents", allowSetters = true)
    private Language language;

    @JsonIgnore
    @ManyToMany(mappedBy = "accents")
    private Set<Narrator> narrators = new HashSet<>();

}
