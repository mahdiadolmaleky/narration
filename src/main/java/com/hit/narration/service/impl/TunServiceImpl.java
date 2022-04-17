package com.hit.narration.service.impl;

import com.hit.narration.domain.Tun;
import com.hit.narration.repository.TunRepository;
import com.hit.narration.service.TunService;
import com.hit.narration.service.dto.TunDTO;
import com.hit.narration.service.mapper.TunMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TunServiceImpl implements TunService {

    private final TunRepository tunRepository;

    private final TunMapper tunMapper;

    public TunServiceImpl(TunRepository tunRepository, TunMapper tunMapper) {
        this.tunRepository = tunRepository;
        this.tunMapper = tunMapper;
    }

    @Override
    public TunDTO save(TunDTO tunDTO) {
        Tun tun = tunMapper.toEntity(tunDTO);
        tun = tunRepository.save(tun);
        return tunMapper.toDto(tun);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TunDTO> findAll(Pageable pageable) {
        return tunRepository.findAll(pageable)
                .map(tunMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TunDTO> findOne(Long id) {
        return tunRepository.findById(id)
                .map(tunMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        tunRepository.deleteById(id);
    }
}
