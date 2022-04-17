package com.hit.narration.service;

import com.hit.narration.service.dto.NarrationProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NarrationProjectService {

    NarrationProjectDTO save(NarrationProjectDTO narrationProjectDTO);

    Page<NarrationProjectDTO> findAll(Pageable pageable);

    Optional<NarrationProjectDTO> findOne(Long id);

    void delete(Long id);
}
