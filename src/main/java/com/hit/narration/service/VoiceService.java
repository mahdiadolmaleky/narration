package com.hit.narration.service;


import com.hit.narration.service.dto.VoiceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VoiceService {

    VoiceDTO save(VoiceDTO voiceDTO);

    Page<VoiceDTO> findAll(Pageable pageable);

    Optional<VoiceDTO> findOne(Long id);

    void delete(Long id);
}
