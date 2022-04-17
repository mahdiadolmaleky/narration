package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.RegionService;
import com.hit.narration.service.dto.RegionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api")
public class RegionResource {


    private final RegionService regionService;

    public RegionResource(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping("/region")
    public ResponseEntity<RegionDTO> createRegion(@RequestBody RegionDTO regionDTO) throws URISyntaxException {
        RegionDTO result = regionService.save(regionDTO);
        return ResponseEntity.created(new URI("/api/region/" + result.getId())).body(result);
    }

    @PutMapping("/region")
    public ResponseEntity<RegionDTO> updateRegion(@RequestBody RegionDTO regionDTO) throws URISyntaxException {
        if (regionDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id");
        }
        RegionDTO result = regionService.save(regionDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/regions")
    public ResponseEntity<Page<RegionDTO>> getAllRegions(Pageable pageable) {
        Page<RegionDTO> page = regionService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/region/{id}")
    public ResponseEntity<RegionDTO> getRegion(@PathVariable Long id) throws ResourceNotFoundException {
        RegionDTO result = regionService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/region/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
        regionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
