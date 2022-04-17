package com.hit.narration.service;

import com.hit.narration.service.dto.LanguageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LanguageService {

    LanguageDTO save(LanguageDTO languageDTO);

    Page<LanguageDTO> findAll(Pageable pageable);

    Optional<LanguageDTO> findOne(Long id);

    void delete(Long id);
}
