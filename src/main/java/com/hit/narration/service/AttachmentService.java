package com.hit.narration.service;

import com.hit.narration.service.dto.AttachmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface AttachmentService {

    AttachmentDTO save(AttachmentDTO attachmentDTO);

    AttachmentDTO getAttachment(Long id, MultipartFile file);

    Page<AttachmentDTO> findAll(Pageable pageable);

    Optional<AttachmentDTO> findOne(Long id);

    void delete(Long id);
}
