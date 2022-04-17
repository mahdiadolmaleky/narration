package com.hit.narration.service.impl;

import com.hit.narration.domain.Narrator;
import com.hit.narration.repository.NarratorRepository;
import com.hit.narration.service.NarratorService;
import com.hit.narration.service.dto.NarratorDTO;
import com.hit.narration.service.mapper.NarratorMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class NarratorServiceImpl implements NarratorService {

    private final NarratorRepository narratorRepository;

    private final NarratorMapper narratorMapper;

    public NarratorServiceImpl(NarratorRepository narratorRepository, NarratorMapper narratorMapper) {
        this.narratorRepository = narratorRepository;
        this.narratorMapper = narratorMapper;
    }


    @Override
    public NarratorDTO save(NarratorDTO narratorDTO) {
        Narrator narrator = narratorMapper.toEntity(narratorDTO);
        narrator = narratorRepository.save(narrator);
        return narratorMapper.toDto(narrator);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NarratorDTO> findAll(Pageable pageable) {
        return narratorRepository.findAll(pageable)
                .map(narratorMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<NarratorDTO> findOne(Long id) {
        return narratorRepository.findById(id)
                .map(narratorMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        narratorRepository.deleteById(id);
    }
}
