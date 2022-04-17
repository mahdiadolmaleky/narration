package com.hit.narration.service;

import com.hit.narration.service.dto.NarratorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NarratorService {

    NarratorDTO save(NarratorDTO narratorDTO);

    Page<NarratorDTO> findAll(Pageable pageable);

    Optional<NarratorDTO> findOne(Long id);

    void delete(Long id);
}
