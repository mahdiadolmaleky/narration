package com.hit.narration.service;

import com.hit.narration.service.dto.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoleService {

    RoleDTO save(RoleDTO roleDTO);

    Page<RoleDTO> findAll(Pageable pageable);

    Optional<RoleDTO> findOne(Long id);

    void delete(Long id);
}
