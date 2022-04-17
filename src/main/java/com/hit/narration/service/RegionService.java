package com.hit.narration.service;

import com.hit.narration.service.dto.RegionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RegionService {

    RegionDTO save(RegionDTO regionDTO);

    Page<RegionDTO> findAll(Pageable pageable);

    Optional<RegionDTO> findOne(Long id);

    void delete(Long id);
}
