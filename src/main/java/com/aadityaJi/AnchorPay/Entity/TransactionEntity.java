package com.aadityaJi.AnchorPay.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    @NotNull(message = "Sender's id Can not be null")
    private Long senderId;

    @Column(nullable = false)
    @NotNull(message = "Receiver id Can not be null")
    private Long receiverId;


    @Column(scale = 4,precision = 19,nullable = false)
    @NotNull(message = "Amount Can not be null")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String description;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public enum Type{
        CREDIT,
        DEBIT,
        TRANSFER
    };

    public enum Status{
        SUCCESS,
        FAILED,
        PENDING
    }

}
