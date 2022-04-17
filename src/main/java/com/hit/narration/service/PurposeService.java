package com.hit.narration.service;

import com.hit.narration.service.dto.PurposeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PurposeService {


    PurposeDTO save(PurposeDTO purposeDTO);

    Page<PurposeDTO> findAll(Pageable pageable);

    Optional<PurposeDTO> findOne(Long id);

    void delete(Long id);
}
