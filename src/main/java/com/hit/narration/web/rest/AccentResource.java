package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.AccentService;
import com.hit.narration.service.dto.AccentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class AccentResource {

    private final AccentService accentService;

    public AccentResource(AccentService accentService) {
        this.accentService = accentService;
    }

    @PostMapping("/accent")
    public ResponseEntity<AccentDTO> createAccent(@RequestBody AccentDTO accentDTO) throws URISyntaxException {
        AccentDTO result = accentService.save(accentDTO);
        return ResponseEntity.created(new URI("/api/accent/" + result.getId())).body(result);
    }

    @PutMapping("/accent")
    public ResponseEntity<AccentDTO> updateAccent(@RequestBody AccentDTO accentDTO) {
        if (accentDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id" + "idnull");
        }
        AccentDTO result = accentService.save(accentDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/accents")
    public ResponseEntity<Page<AccentDTO>> getAllAccents(Pageable pageable) {
        Page<AccentDTO> page = accentService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/accent/{id}")
    public ResponseEntity<AccentDTO> getAccent(@PathVariable Long id) throws ResourceNotFoundException {
        AccentDTO result = accentService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/accent/{id}")
    public ResponseEntity<Void> deleteAccent(@PathVariable Long id) {
        accentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
