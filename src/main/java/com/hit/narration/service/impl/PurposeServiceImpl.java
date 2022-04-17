package com.hit.narration.service.impl;

import com.hit.narration.domain.Purpose;
import com.hit.narration.repository.PurposeRepository;
import com.hit.narration.service.PurposeService;
import com.hit.narration.service.dto.PurposeDTO;
import com.hit.narration.service.mapper.PurposeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PurposeServiceImpl implements PurposeService {

    private final PurposeRepository purposeRepository;

    private final PurposeMapper purposeMapper;

    public PurposeServiceImpl(PurposeRepository purposeRepository, PurposeMapper purposeMapper) {
        this.purposeRepository = purposeRepository;
        this.purposeMapper = purposeMapper;
    }

    @Override
    public PurposeDTO save(PurposeDTO purposeDTO) {
        Purpose purpose = purposeMapper.toEntity(purposeDTO);
        purpose = purposeRepository.save(purpose);
        return purposeMapper.toDto(purpose);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PurposeDTO> findAll(Pageable pageable) {
        return purposeRepository.findAll(pageable)
            .map(purposeMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PurposeDTO> findOne(Long id) {
        return purposeRepository.findById(id)
            .map(purposeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        purposeRepository.deleteById(id);
    }
}
