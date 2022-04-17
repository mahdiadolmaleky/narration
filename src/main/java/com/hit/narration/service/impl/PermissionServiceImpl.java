package com.hit.narration.service.impl;

import com.hit.narration.domain.Permission;
import com.hit.narration.domain.Role;
import com.hit.narration.repository.PermissionRepository;
import com.hit.narration.service.PermissionService;
import com.hit.narration.service.dto.PermissionDTO;
import com.hit.narration.service.dto.RoleDTO;
import com.hit.narration.service.mapper.PermissionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    private final PermissionMapper permissionMapper;

    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }


    @Override
    public PermissionDTO save(PermissionDTO permissionDTO) {
        Permission permission = permissionMapper.toEntity(permissionDTO);
        permission = permissionRepository.save(permission);
        return permissionMapper.toDto(permission);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PermissionDTO> findAll(Pageable pageable) {
        return permissionRepository.findAll(pageable)
                .map(permissionMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PermissionDTO> findOne(Long id) {
        return permissionRepository.findById(id)
                .map(permissionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }
}
