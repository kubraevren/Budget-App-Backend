package com.kubraevren.dto;

import com.kubraevren.enums.Category;
import com.kubraevren.enums.TransactionType;
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
    private Category category;
    private TransactionType transactionType;
    private String description;
    private LocalDate date;

}