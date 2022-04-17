package com.hit.narration.service.impl;

import com.hit.narration.domain.SocialMedia;
import com.hit.narration.repository.SocialMediaRepository;
import com.hit.narration.service.SocialMediaService;
import com.hit.narration.service.dto.SocialMediaDTO;
import com.hit.narration.service.mapper.SocialMediaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class SocialMediaServiceImpl implements SocialMediaService {

    private final SocialMediaRepository socialMediaRepository;

    private final SocialMediaMapper socialMediaMapper;

    public SocialMediaServiceImpl(SocialMediaRepository socialMediaRepository, SocialMediaMapper socialMediaMapper) {
        this.socialMediaRepository = socialMediaRepository;
        this.socialMediaMapper = socialMediaMapper;
    }

    @Override
    public SocialMediaDTO save(SocialMediaDTO socialMediaDTO) {
        SocialMedia socialMedia = socialMediaMapper.toEntity(socialMediaDTO);
        socialMedia = socialMediaRepository.save(socialMedia);
        return socialMediaMapper.toDto(socialMedia);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SocialMediaDTO> findAll(Pageable pageable) {
        return socialMediaRepository.findAll(pageable)
            .map(socialMediaMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SocialMediaDTO> findOne(Long id) {
        return socialMediaRepository.findById(id)
            .map(socialMediaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        socialMediaRepository.deleteById(id);
    }
}
