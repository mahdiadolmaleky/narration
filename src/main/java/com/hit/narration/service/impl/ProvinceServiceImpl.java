package com.hit.narration.service.impl;

import com.hit.narration.domain.Province;
import com.hit.narration.repository.ProvinceRepository;
import com.hit.narration.service.ProvinceService;
import com.hit.narration.service.dto.ProvinceDTO;
import com.hit.narration.service.mapper.ProvinceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;

    private final ProvinceMapper provinceMapper;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ProvinceMapper provinceMapper) {
        this.provinceRepository = provinceRepository;
        this.provinceMapper = provinceMapper;
    }

    @Override
    public ProvinceDTO save(ProvinceDTO provinceDTO) {
        Province province = provinceMapper.toEntity(provinceDTO);
        province = provinceRepository.save(province);
        return provinceMapper.toDto(province);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProvinceDTO> findAll(Pageable pageable) {
        return provinceRepository.findAll(pageable)
            .map(provinceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProvinceDTO> findOne(Long id) {
        return provinceRepository.findById(id)
            .map(provinceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        provinceRepository.deleteById(id);
    }
}
