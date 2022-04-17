package com.hit.narration.domain;

import com.hit.narration.domain.enumeration.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nr_person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private SexEnum sex;

    @NotNull
    @Size(min = 10, max = 10)
    @Column(name = "national_code", length = 10, nullable = false, unique = true)
    private String nationalCode;

    @Column(name = "birth_date")
    private Instant birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "about")
    private String about;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

}
