package com.hit.narration.service;

import com.hit.narration.service.dto.PermissionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PermissionService {

    PermissionDTO save(PermissionDTO permissionDTO);

    Page<PermissionDTO> findAll(Pageable pageable);

    Optional<PermissionDTO> findOne(Long id);

    void delete(Long id);
}
