package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.AttachmentService;
import com.hit.narration.service.dto.AttachmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class AttachmentResource {

    private final AttachmentService attachmentService;

    public AttachmentResource(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }


    @PermitAll
    @PostMapping("/attachment")
    public ResponseEntity<AttachmentDTO> createAttachment(@Valid @RequestBody AttachmentDTO attachmentDTO) throws URISyntaxException {
        AttachmentDTO result = attachmentService.save(attachmentDTO);
        return ResponseEntity.created(new URI("/api/attachment/" + result.getId())).body(result);
    }

    @PermitAll
    @PutMapping("/attachment")
    public ResponseEntity<AttachmentDTO> updateAttachment(@Valid @RequestBody AttachmentDTO attachmentDTO) throws URISyntaxException {
        if (attachmentDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id");
        }
        AttachmentDTO result = attachmentService.save(attachmentDTO);
        return ResponseEntity.ok().body(result);
    }

    @PermitAll
    @GetMapping("/attachments")
    public ResponseEntity<Page<AttachmentDTO>> getAllAttachments(Pageable pageable, @RequestParam(required = false) String filter) {
        Page<AttachmentDTO> page = attachmentService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PermitAll
    @GetMapping("/attachment/{id}")
    public ResponseEntity<AttachmentDTO> getAttachment(@PathVariable Long id) throws ResourceNotFoundException {
        AttachmentDTO result = attachmentService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @PermitAll
    @DeleteMapping("/attachment/{id}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable Long id) {
        attachmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
