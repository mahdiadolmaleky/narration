package com.hit.narration.service;

import com.hit.narration.service.dto.CityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CityService {

    CityDTO save(CityDTO cityDTO);

    Page<CityDTO> findAll(Pageable pageable);

    Optional<CityDTO> findOne(Long id);

    void delete(Long id);
}
