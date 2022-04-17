package com.hit.narration.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hit.narration.domain.enumeration.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nr_payment")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethodEnum paymentMethod;

    @Column(name = "payment_percent")
    private Integer paymentPercent;

    @Column(name = "payment_amount")
    private Long paymentAmount;

    @Column(name = "payment_date")
    private Instant paymentDate = Instant.now();

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(unique = true)
    private Attachment paymentReceipt;

    @ManyToOne
    @JsonIgnoreProperties(value = "payments", allowSetters = true)
    private NarrationProject narrationProject;
}
