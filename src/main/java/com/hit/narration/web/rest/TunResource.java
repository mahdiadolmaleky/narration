package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.TunService;
import com.hit.narration.service.dto.TunDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class TunResource {

    private final TunService tunService;

    public TunResource(TunService tunService) {
        this.tunService = tunService;
    }

    @PostMapping("/tun")
    public ResponseEntity<TunDTO> createTun(@RequestBody TunDTO tunDTO) throws URISyntaxException {
        TunDTO result = tunService.save(tunDTO);
        return ResponseEntity.created(new URI("/api/tun/" + result.getId())).body(result);
    }

    @PutMapping("/tun")
    public ResponseEntity<TunDTO> updateTun(@RequestBody TunDTO tunDTO) throws URISyntaxException {
        if (tunDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id" + "idnull");
        }
        TunDTO result = tunService.save(tunDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/tuns")
    public ResponseEntity<Page<TunDTO>> getAllTuns(Pageable pageable) {
        Page<TunDTO> page = tunService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/tun/{id}")
    public ResponseEntity<TunDTO> getTun(@PathVariable Long id) throws ResourceNotFoundException {
        TunDTO result = tunService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/tun/{id}")
    public ResponseEntity<Void> deleteTun(@PathVariable Long id) {
        tunService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
