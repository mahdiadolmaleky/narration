package com.hit.narration.service.dto;

import com.hit.narration.domain.Permission;
import com.hit.narration.domain.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

@Data
public class RoleDTO implements Serializable {

    private Long id;

    private String name;
}
