package com.hit.narration.service;

import com.hit.narration.service.dto.ProvinceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ProvinceService {

    ProvinceDTO save(ProvinceDTO provinceDTO);

    Page<ProvinceDTO> findAll(Pageable pageable);

    Optional<ProvinceDTO> findOne(Long id);

    void delete(Long id);
}
