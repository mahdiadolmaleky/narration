package com.hit.narration.service;

import com.hit.narration.service.dto.SocialMediaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SocialMediaService {

    SocialMediaDTO save(SocialMediaDTO socialMediaDTO);

    Page<SocialMediaDTO> findAll(Pageable pageable);

    Optional<SocialMediaDTO> findOne(Long id);

    void delete(Long id);
}
