package com.kubraevren.model;

import com.kubraevren.enums.Category;
import com.kubraevren.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="expenses")
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false)
    private UserEntity user; // Harcamayı yapan kullanıcıya referans

    @Column(nullable=false)
    private BigDecimal amount;

    @Column(length=255)
    private String description;

    @Enumerated(EnumType.STRING) // ÖNEMLİ!
    @Column(nullable=false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TransactionType transactionType;

    @Column(nullable=false)
    private LocalDate date; // sadece tarih

    private LocalDateTime createdAt = LocalDateTime.now();

}

