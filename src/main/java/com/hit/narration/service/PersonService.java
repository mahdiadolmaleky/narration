package com.hit.narration.service;


import com.hit.narration.service.dto.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonService {

    PersonDTO save(PersonDTO personDTO);

    Page<PersonDTO> findAll(Pageable pageable);

    Optional<PersonDTO> findOne(Long id);

    void delete(Long id);

    PersonDTO findByNationalCode(String code);

}
