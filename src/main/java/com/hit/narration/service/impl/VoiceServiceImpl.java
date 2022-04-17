package com.hit.narration.service.impl;

import com.hit.narration.domain.Voice;
import com.hit.narration.repository.VoiceRepository;
import com.hit.narration.service.VoiceService;
import com.hit.narration.service.dto.VoiceDTO;
import com.hit.narration.service.mapper.VoiceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class VoiceServiceImpl implements VoiceService {

    private final VoiceRepository voiceRepository;

    private final VoiceMapper voiceMapper;

    public VoiceServiceImpl(VoiceRepository voiceRepository, VoiceMapper voiceMapper) {
        this.voiceRepository = voiceRepository;
        this.voiceMapper = voiceMapper;
    }


    @Override
    public VoiceDTO save(VoiceDTO voiceDTO) {
        Voice voice = voiceMapper.toEntity(voiceDTO);
        voice = voiceRepository.save(voice);
        return voiceMapper.toDto(voice);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VoiceDTO> findAll(Pageable pageable) {
        return voiceRepository.findAll(pageable)
                .map(voiceMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<VoiceDTO> findOne(Long id) {
        return voiceRepository.findById(id)
                .map(voiceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        voiceRepository.deleteById(id);
    }
}
