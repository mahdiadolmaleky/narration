package com.hit.narration.service;

import com.hit.narration.service.dto.CountryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CountryService {

    CountryDTO save(CountryDTO countryDTO);

    Page<CountryDTO> findAll(Pageable pageable);

    Optional<CountryDTO> findOne(Long id);

    void delete(Long id);
}
