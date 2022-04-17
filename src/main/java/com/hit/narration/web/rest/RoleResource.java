package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.RoleService;
import com.hit.narration.service.dto.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class RoleResource {

    private final RoleService roleService;

    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) throws URISyntaxException {
        RoleDTO result = roleService.save(roleDTO);
        return ResponseEntity.created(new URI("/api/roles/" + result.getId())).body(result);
    }

    @PutMapping("/roles")
    public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO roleDTO) throws URISyntaxException {
        if (roleDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id" + "idnull");
        }
        RoleDTO result = roleService.save(roleDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/roles")
    public ResponseEntity<Page<RoleDTO>> getAllRoles(Pageable pageable) {
        Page<RoleDTO> page = roleService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable Long id) throws ResourceNotFoundException {
        RoleDTO result = roleService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
