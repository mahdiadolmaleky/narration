package com.hit.narration.service.impl;

import com.hit.narration.domain.Role;
import com.hit.narration.repository.RoleRepository;
import com.hit.narration.service.RoleService;
import com.hit.narration.service.dto.RoleDTO;
import com.hit.narration.service.mapper.RoleMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RoleDTO> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable)
                .map(roleMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RoleDTO> findOne(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
