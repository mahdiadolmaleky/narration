package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.NarratorService;
import com.hit.narration.service.dto.NarratorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class NarratorResource {

    private final NarratorService narratorservice;

    public NarratorResource(NarratorService narratorservice) {
        this.narratorservice = narratorservice;
    }

    @PostMapping("/narrator")
    public ResponseEntity<NarratorDTO> createNarrator(@RequestBody NarratorDTO narratorDTO) throws URISyntaxException {
        NarratorDTO result = narratorservice.save(narratorDTO);
        return ResponseEntity.created(new URI("/api/narrator/" + result.getId())).body(result);
    }

    @PutMapping("/narrator")
    public ResponseEntity<NarratorDTO> updateNarrator(@RequestBody NarratorDTO narratorDTO) throws URISyntaxException {
        if (narratorDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id" + "idnull");
        }
        NarratorDTO result = narratorservice.save(narratorDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/narrators")
    public ResponseEntity<Page<NarratorDTO>> getAllNarrators(Pageable pageable) {
        Page<NarratorDTO> page = narratorservice.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/narrator/{id}")
    public ResponseEntity<NarratorDTO> getNarrator(@PathVariable Long id) throws ResourceNotFoundException {
        NarratorDTO result = narratorservice.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/narrator/{id}")
    public ResponseEntity<Void> deleteNarrator(@PathVariable Long id) {
        narratorservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
