package com.hit.narration.service.impl;

import com.hit.narration.domain.Language;
import com.hit.narration.repository.LanguageRepository;
import com.hit.narration.service.LanguageService;
import com.hit.narration.service.dto.LanguageDTO;
import com.hit.narration.service.mapper.LanguageMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    private final LanguageMapper languageMapper;

    public LanguageServiceImpl(LanguageRepository languageRepository, LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }

    @Override
    public LanguageDTO save(LanguageDTO languageDTO) {
        Language language = languageMapper.toEntity(languageDTO);
        language = languageRepository.save(language);
        return languageMapper.toDto(language);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LanguageDTO> findAll(Pageable pageable) {
        return languageRepository.findAll(pageable)
                .map(languageMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<LanguageDTO> findOne(Long id) {
        return languageRepository.findById(id)
                .map(languageMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        languageRepository.deleteById(id);
    }
}
