package com.hit.narration.service;


import com.hit.narration.service.dto.TunDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TunService {

    TunDTO save(TunDTO tunDTO);

    Page<TunDTO> findAll(Pageable pageable);

    Optional<TunDTO> findOne(Long id);

    void delete(Long id);
}
