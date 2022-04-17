package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.LanguageService;
import com.hit.narration.service.dto.LanguageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api")
public class LanguageResource {

    private final LanguageService languageService;

    public LanguageResource(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/language")
    public ResponseEntity<LanguageDTO> createLanguage(@RequestBody LanguageDTO languageDTO) throws URISyntaxException {
        LanguageDTO result = languageService.save(languageDTO);
        return ResponseEntity.created(new URI("/api/language/" + result.getId())).body(result);
    }

    @PutMapping("/language")
    public ResponseEntity<LanguageDTO> updateLanguage(@RequestBody LanguageDTO languageDTO) throws URISyntaxException {
        if (languageDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id" + "idnull");
        }
        LanguageDTO result = languageService.save(languageDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/languages")
    public ResponseEntity<Page<LanguageDTO>> getAllLanguages(Pageable pageable) {
        Page<LanguageDTO> page = languageService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/language/{id}")
    public ResponseEntity<LanguageDTO> getLanguage(@PathVariable Long id) throws ResourceNotFoundException {
        LanguageDTO result = languageService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/language/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Long id) {
        languageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
