package com.hit.narration.service.impl;

import com.hit.narration.domain.Attachment;
import com.hit.narration.repository.AttachmentRepository;
import com.hit.narration.service.AttachmentService;
import com.hit.narration.service.dto.AttachmentDTO;
import com.hit.narration.service.mapper.AttachmentMapper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    private final AttachmentMapper attachmentMapper;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository, AttachmentMapper attachmentMapper) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentMapper = attachmentMapper;
    }

    @Override
    public AttachmentDTO save(AttachmentDTO attachmentDTO) {
        Attachment attachment = attachmentMapper.toEntity(attachmentDTO);
        attachment = attachmentRepository.save(attachment);
        AttachmentDTO result = attachmentMapper.toDto(attachment);
        return result;
    }

    public AttachmentDTO getAttachment(Long id, MultipartFile file) {
        try {
            AttachmentDTO attachment;
            if (id != null) {
                attachment = findOne(id).get();
            } else {
                attachment = new AttachmentDTO();
            }
            attachment.setDataContentType(file.getContentType());
            attachment.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
            attachment.setData(file.getBytes());
            attachment.setFileSize(file.getSize());
            attachment.setFileName(file.getOriginalFilename());
            return attachment;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AttachmentDTO> findAll(Pageable pageable) {
        return attachmentRepository.findAll(pageable)
            .map(attachmentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AttachmentDTO> findOne(Long id) {
        return attachmentRepository.findById(id)
            .map(attachmentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        attachmentRepository.deleteById(id);
    }

}
