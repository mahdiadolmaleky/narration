package com.hit.narration.web.rest;

import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.service.PaymentService;
import com.hit.narration.service.dto.PaymentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api")
public class PaymentResource {

    private final PaymentService paymentService;

    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) throws URISyntaxException {
        PaymentDTO result = paymentService.save(paymentDTO);
        return ResponseEntity.created(new URI("/api/payment/" + result.getId())).body(result);
    }

    @PutMapping("/payment")
    public ResponseEntity<PaymentDTO> updatePayment(@RequestBody PaymentDTO paymentDTO) throws URISyntaxException {
        if (paymentDTO.getId() == null) {
            new ResourceNotFoundException("Invalid id");
        }
        PaymentDTO result = paymentService.save(paymentDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/payments")
    public ResponseEntity<Page<PaymentDTO>> getAllPayments(Pageable pageable) {
        Page<PaymentDTO> page = paymentService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id) throws ResourceNotFoundException {
        PaymentDTO result = paymentService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/payment/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
