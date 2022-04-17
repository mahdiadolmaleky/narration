package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.SocialMediaService;
import com.hit.narration.service.dto.SocialMediaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api")
public class SocialMediaResource {

    private final SocialMediaService socialMediaService;

    public SocialMediaResource(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @PostMapping("/social-media")
    public ResponseEntity<SocialMediaDTO> createSocialMedia(@RequestBody SocialMediaDTO socialMediaDTO) throws URISyntaxException {
        SocialMediaDTO result = socialMediaService.save(socialMediaDTO);
        return ResponseEntity.created(new URI("/api/social-media/" + result.getId())).body(result);
    }

    @PutMapping("/social-media")
    public ResponseEntity<SocialMediaDTO> updateSocialMedia(@RequestBody SocialMediaDTO socialMediaDTO) throws URISyntaxException {
        if (socialMediaDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id");
        }
        SocialMediaDTO result = socialMediaService.save(socialMediaDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/social-medias")
    public ResponseEntity<Page<SocialMediaDTO>> getAllSocialMedias(Pageable pageable) {
        Page<SocialMediaDTO> page = socialMediaService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/social-media/{id}")
    public ResponseEntity<SocialMediaDTO> getSocialMedia(@PathVariable Long id) throws ResourceNotFoundException {
        SocialMediaDTO result = socialMediaService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/social-media/{id}")
    public ResponseEntity<Void> deleteSocialMedia(@PathVariable Long id) {
        socialMediaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
