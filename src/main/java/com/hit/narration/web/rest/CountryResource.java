package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.CountryService;
import com.hit.narration.service.dto.CountryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api")
public class CountryResource {

    private final CountryService countryService;

    public CountryResource(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/country")
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) throws URISyntaxException {
        CountryDTO result = countryService.save(countryDTO);
        return ResponseEntity.created(new URI("/api/countries/" + result.getId())).body(result);
    }

    @PutMapping("/country")
    public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO countryDTO) {
        if (countryDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id" + "idnull");
        }
        CountryDTO result = countryService.save(countryDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/countries")
    public ResponseEntity<Page<CountryDTO>> getAllCountries(Pageable pageable) {
        Page<CountryDTO> page = countryService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<CountryDTO> getCountry(@PathVariable Long id) throws ResourceNotFoundException {
        CountryDTO result = countryService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
