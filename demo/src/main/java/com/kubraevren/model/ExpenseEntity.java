package com.kubraevren.model;

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

    @Column(nullable=false)
    private String category; // e.g., FOOD, TRANSPORT, RENT, OTHER

    @Column(nullable=false)
    private LocalDate date; // sadece tarih

    private LocalDateTime createdAt = LocalDateTime.now();

}

