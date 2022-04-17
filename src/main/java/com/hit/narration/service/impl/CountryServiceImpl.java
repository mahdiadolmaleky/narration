package com.hit.narration.service.impl;

import com.hit.narration.domain.Country;
import com.hit.narration.repository.CountryRepository;
import com.hit.narration.service.CountryService;
import com.hit.narration.service.dto.CountryDTO;
import com.hit.narration.service.mapper.CountryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public CountryDTO save(CountryDTO countryDTO) {
        Country country = countryMapper.toEntity(countryDTO);
        country = countryRepository.save(country);
        return countryMapper.toDto(country);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CountryDTO> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable)
                .map(countryMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CountryDTO> findOne(Long id) {
        return countryRepository.findById(id)
                .map(countryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
