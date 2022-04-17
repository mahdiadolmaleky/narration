package com.hit.narration.service.dto;

import com.hit.narration.domain.Role;

import lombok.Data;
import java.io.Serializable;
import java.util.Set;

@Data
public class PermissionDTO implements Serializable {

    private Long id;

    private String workspace;

    private Boolean create;

    private Boolean update;

    private Boolean read;

    private Boolean delete;

}
