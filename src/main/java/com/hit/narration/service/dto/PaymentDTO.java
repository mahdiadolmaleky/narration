package com.hit.narration.service.dto;

import com.hit.narration.domain.Attachment;
import com.hit.narration.domain.enumeration.PaymentMethodEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class PaymentDTO implements Serializable {

    private Long id;

    private PaymentMethodEnum paymentMethod;

    private Integer paymentPercent;

    private Long paymentAmount;

    private Instant paymentDate;

    private String description;

    private Attachment paymentReceipt;
    private Long paymentReceiptId;

    private Long narrationProjectId;

}
