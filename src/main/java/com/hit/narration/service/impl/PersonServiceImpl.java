package com.hit.narration.service.impl;

import com.hit.narration.domain.Person;
import com.hit.narration.repository.PersonRepository;
import com.hit.narration.service.PersonService;
import com.hit.narration.service.dto.PersonDTO;
import com.hit.narration.service.mapper.PersonMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository personRepository,
                             PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PersonDTO> findAll(Pageable pageable) {
        return personRepository.findAll(pageable)
            .map(personMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonDTO> findOne(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.map(personMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonDTO findByNationalCode(String code) {
        return personMapper.toDto(personRepository.findByNationalCode(code));
    }

}
