package com.hit.narration.service.dto;

import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AttachmentDTO implements Serializable {

    public static AttachmentDTO create(MultipartFile file, Long attachmentId) {

        if (file == null && attachmentId == null)
            return null;

        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setId(attachmentId);

        if (file != null) {
            try {
                attachmentDTO.setFileName(file.getOriginalFilename());
                attachmentDTO.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
                attachmentDTO.setDataContentType(file.getContentType());
                attachmentDTO.setFileSize(file.getSize());
                attachmentDTO.setData(file.getBytes());
            } catch (Exception e) {
                return null;
            }
        }

        return attachmentDTO;
    }
    private Long id;

    @Lob
    private byte[] data;

    private String dataContentType;
    private String title;

    private String description;

    @NotNull
    private String fileName;

    @NotNull
    private String extension;

    @NotNull
    private Long fileSize;
}
