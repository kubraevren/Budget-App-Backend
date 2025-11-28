package com.kubraevren.dto;

import jakarta.validation.constraints.*;
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
public class ExpenseCreateDto {

    @NotNull(message = "Miktar boş olamaz")
    @DecimalMin(value = "0.01", message = "Miktar en az 0.01 olmalıdır")
    private BigDecimal amount;

    @NotBlank(message = "Kategori boş olamaz")
    private String category;  // e.g., FOOD, TRANSPORT

    private String description;  // Bu opsiyonel, validasyon gerekmez

    @NotNull(message = "Tarih boş olamaz")
    private LocalDate date;
}