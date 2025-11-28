package com.kubraevren.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {

    private Long id; // harcama kaydının id'si
    private BigDecimal amount;
    private String category;
    private String description;
    private LocalDate date;

}