package com.hit.narration.service.impl;

import com.hit.narration.domain.City;
import com.hit.narration.repository.CityRepository;
import com.hit.narration.service.CityService;
import com.hit.narration.service.dto.CityDTO;
import com.hit.narration.service.mapper.CityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    public CityDTO save(CityDTO cityDTO) {
        City city = cityMapper.toEntity(cityDTO);
        city = cityRepository.save(city);
        return cityMapper.toDto(city);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CityDTO> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable)
                .map(cityMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CityDTO> findOne(Long id) {
        return cityRepository.findById(id)
                .map(cityMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }
}
