package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.VoiceService;
import com.hit.narration.service.dto.VoiceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api")
public class VoiceResource {

    private final VoiceService voiceService;

    public VoiceResource(VoiceService voiceService) {
        this.voiceService = voiceService;
    }

    @PostMapping("/voice")
    public ResponseEntity<VoiceDTO> createVoice(@RequestBody VoiceDTO voiceDTO) throws URISyntaxException {
        VoiceDTO result = voiceService.save(voiceDTO);
        return ResponseEntity.created(new URI("/api/voice/" + result.getId())).body(result);
    }

    @PutMapping("/voice")
    public ResponseEntity<VoiceDTO> updateVoice(@RequestBody VoiceDTO voiceDTO) throws URISyntaxException {
        if (voiceDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id");
        }
        VoiceDTO result = voiceService.save(voiceDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/voices")
    public ResponseEntity<Page<VoiceDTO>> getAllVoices(Pageable pageable) {
        Page<VoiceDTO> page = voiceService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/voice/{id}")
    public ResponseEntity<VoiceDTO> getVoice(@PathVariable Long id) throws ResourceNotFoundException {
        VoiceDTO result = voiceService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/voice/{id}")
    public ResponseEntity<Void> deleteVoice(@PathVariable Long id) {
        voiceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
