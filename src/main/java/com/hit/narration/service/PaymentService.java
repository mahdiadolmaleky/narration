package com.hit.narration.service;


import com.hit.narration.service.dto.PaymentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PaymentService {

    PaymentDTO save(PaymentDTO paymentDTO);

    Page<PaymentDTO> findAll(Pageable pageable);

    Optional<PaymentDTO> findOne(Long id);

    void delete(Long id);
}
