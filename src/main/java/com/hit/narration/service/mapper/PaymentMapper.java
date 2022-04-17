package com.hit.narration.service.mapper;

import com.hit.narration.domain.Payment;
import com.hit.narration.service.dto.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {NarrationProjectMapper.class,AttachmentMapper.class})
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {

    @Mapping(source = "narrationProject.id", target = "narrationProjectId")
    @Mapping(source = "paymentReceipt.id", target = "paymentReceiptId")
    PaymentDTO toDto(Payment payment);

    @Mapping(source = "narrationProjectId", target = "narrationProject")
    Payment toEntity(PaymentDTO paymentDTO);

    default Payment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(id);
        return payment;
    }
}
