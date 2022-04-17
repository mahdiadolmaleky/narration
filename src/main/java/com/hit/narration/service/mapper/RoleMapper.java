package com.hit.narration.service.mapper;

import com.hit.narration.domain.Role;
import com.hit.narration.service.dto.RoleDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PermissionMapper.class})
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

    default Role fromId(Long id) {
        if (id == null) {
            return null;
        }
        Role role = new Role();
        role.setId(id);
        return role;
    }
}
