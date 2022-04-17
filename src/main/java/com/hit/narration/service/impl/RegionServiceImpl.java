package com.hit.narration.service.impl;

import com.hit.narration.domain.Region;
import com.hit.narration.repository.RegionRepository;
import com.hit.narration.service.RegionService;
import com.hit.narration.service.dto.RegionDTO;
import com.hit.narration.service.mapper.RegionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    private final RegionMapper regionMapper;

    public RegionServiceImpl(RegionRepository regionRepository, RegionMapper regionMapper) {
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    @Override
    public RegionDTO save(RegionDTO regionDTO) {
        Region region = regionMapper.toEntity(regionDTO);
        region = regionRepository.save(region);
        return regionMapper.toDto(region);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RegionDTO> findAll(Pageable pageable) {
        return regionRepository.findAll(pageable)
            .map(regionMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RegionDTO> findOne(Long id) {
        return regionRepository.findById(id)
            .map(regionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        regionRepository.deleteById(id);
    }
}
