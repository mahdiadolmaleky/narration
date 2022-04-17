package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.CityService;
import com.hit.narration.service.dto.CityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api")
public class CityResource {

    private final CityService cityService;

    public CityResource(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/city")
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) throws URISyntaxException {
        CityDTO result = cityService.save(cityDTO);
        return ResponseEntity.created(new URI("/api/city/" + result.getId())).body(result);
    }

    @PutMapping("/city")
    public ResponseEntity<CityDTO> updateCity(@RequestBody CityDTO cityDTO) throws URISyntaxException {
        if (cityDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id" + "idnull");
        }
        CityDTO result = cityService.save(cityDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/cities")
    public ResponseEntity<Page<CityDTO>> getAllCities(Pageable pageable) {
        Page<CityDTO> page = cityService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<CityDTO> getCity(@PathVariable Long id) throws ResourceNotFoundException {
        CityDTO result = cityService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
