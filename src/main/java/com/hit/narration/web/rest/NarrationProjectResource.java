package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.NarrationProjectService;
import com.hit.narration.service.dto.NarrationProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class NarrationProjectResource {

    private final NarrationProjectService narrationProjectService;

    public NarrationProjectResource(NarrationProjectService narrationProjectService) {
        this.narrationProjectService = narrationProjectService;
    }

    @PostMapping("/narration-project")
    public ResponseEntity<NarrationProjectDTO> createNarrationProject(@RequestBody NarrationProjectDTO narrationProjectDTO) throws URISyntaxException {
        NarrationProjectDTO result = narrationProjectService.save(narrationProjectDTO);
        return ResponseEntity.created(new URI("/api/narration-project/" + result.getId())).body(result);
    }

    @PutMapping("/narration-project")
    public ResponseEntity<NarrationProjectDTO> updateNarrationProject(@RequestBody NarrationProjectDTO narrationProjectDTO) throws URISyntaxException {

        if (narrationProjectDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id" + "idnull");
        }
        NarrationProjectDTO result = narrationProjectService.save(narrationProjectDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/narration-projects")
    public ResponseEntity<Page<NarrationProjectDTO>> getAllNarrationProjects(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        Page<NarrationProjectDTO> page = narrationProjectService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/narration-project/{id}")
    public ResponseEntity<NarrationProjectDTO> getNarrationProject(@PathVariable Long id) throws ResourceNotFoundException {
        NarrationProjectDTO result = narrationProjectService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/narration-project/{id}")
    public ResponseEntity<Void> deleteNarrationProject(@PathVariable Long id) {
        narrationProjectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
