package com.hit.narration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nr_permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String workspace;

    @Basic
    private Boolean create = Boolean.FALSE;

    @Basic
    private Boolean update = Boolean.FALSE;

    @Basic
    private Boolean read = Boolean.FALSE;

    @Basic
    private Boolean delete = Boolean.FALSE;
}
