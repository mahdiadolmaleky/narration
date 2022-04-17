package com.hit.narration.service;

import com.hit.narration.service.dto.AccentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccentService {

    AccentDTO save(AccentDTO accentDTO);

    Page<AccentDTO> findAll(Pageable pageable);

    Optional<AccentDTO> findOne(Long id);

    void delete(Long id);
}
