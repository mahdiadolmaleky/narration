package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.ProvinceService;
import com.hit.narration.service.dto.ProvinceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api")
public class ProvinceResource {

    private final ProvinceService provinceService;

    public ProvinceResource(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @PostMapping("/province")
    public ResponseEntity<ProvinceDTO> createProvince(@RequestBody ProvinceDTO provinceDTO) throws URISyntaxException {

        ProvinceDTO result = provinceService.save(provinceDTO);
        return ResponseEntity.created(new URI("/api/province/" + result.getId())).body(result);
    }

    @PutMapping("/province")
    public ResponseEntity<ProvinceDTO> updateProvince(@RequestBody ProvinceDTO provinceDTO) throws URISyntaxException {
        if (provinceDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id" + "idnull");
        }
        ProvinceDTO result = provinceService.save(provinceDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/provinces")
    public ResponseEntity<Page<ProvinceDTO>> getAllProvinces(Pageable pageable) {
        Page<ProvinceDTO> page = provinceService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/province/{id}")
    public ResponseEntity<ProvinceDTO> getProvince(@PathVariable Long id) throws ResourceNotFoundException {
        ProvinceDTO result = provinceService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/province/{id}")
    public ResponseEntity<Void> deleteProvince(@PathVariable Long id) {
        provinceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
