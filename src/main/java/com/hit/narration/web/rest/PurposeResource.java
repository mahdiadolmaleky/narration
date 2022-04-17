package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.PurposeService;
import com.hit.narration.service.dto.PurposeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api")
public class PurposeResource {

    private final PurposeService purposeService;

    public PurposeResource(PurposeService purposeService) {
        this.purposeService = purposeService;
    }

    @PostMapping("/purpose")
    public ResponseEntity<PurposeDTO> createPurpose(@RequestBody PurposeDTO purposeDTO) throws URISyntaxException {
        PurposeDTO result = purposeService.save(purposeDTO);
        return ResponseEntity.created(new URI("/api/purpose/" + result.getId())).body(result);
    }

    @PutMapping("/purpose")
    public ResponseEntity<PurposeDTO> updatePurpose(@RequestBody PurposeDTO purposeDTO) throws URISyntaxException {
        if (purposeDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id" + "idnull");
        }
        PurposeDTO result = purposeService.save(purposeDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/purposes")
    public ResponseEntity<Page<PurposeDTO>> getAllPurposes(Pageable pageable) {
        Page<PurposeDTO> page = purposeService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/purpose/{id}")
    public ResponseEntity<PurposeDTO> getPurpose(@PathVariable Long id) throws ResourceNotFoundException {
        PurposeDTO result = purposeService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/purpose/{id}")
    public ResponseEntity<Void> deletePurpose(@PathVariable Long id) {
        purposeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
