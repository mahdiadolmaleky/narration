package com.hit.narration.service.impl;

import com.hit.narration.domain.Accent;
import com.hit.narration.repository.AccentRepository;
import com.hit.narration.service.AccentService;
import com.hit.narration.service.dto.AccentDTO;
import com.hit.narration.service.mapper.AccentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccentServiceImpl implements AccentService {

    private final AccentRepository accentRepository;

    private final AccentMapper accentMapper;

    public AccentServiceImpl(AccentRepository accentRepository, AccentMapper accentMapper) {
        this.accentRepository = accentRepository;
        this.accentMapper = accentMapper;
    }

    @Override
    public AccentDTO save(AccentDTO accentDTO) {
        Accent accent = accentMapper.toEntity(accentDTO);
        accent = accentRepository.save(accent);
        return accentMapper.toDto(accent);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccentDTO> findAll(Pageable pageable) {
        return accentRepository.findAll(pageable)
                .map(accentMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AccentDTO> findOne(Long id) {
        return accentRepository.findById(id)
                .map(accentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        accentRepository.deleteById(id);
    }
}
