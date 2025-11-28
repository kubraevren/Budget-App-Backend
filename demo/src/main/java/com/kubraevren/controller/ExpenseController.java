package com.kubraevren.controller;

import com.kubraevren.dto.ExpenseCreateDto;
import com.kubraevren.dto.ExpenseDto;
import com.kubraevren.mapper.ExpenseMapper;
import com.kubraevren.security.CustomUserDetails;
import com.kubraevren.security.CustomUserDetailsService;
import com.kubraevren.service.ExpenseService;
import com.kubraevren.model.ExpenseEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses") // Temel URL [1]
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseMapper mapper;

    @PostMapping
    public ResponseEntity createExpense(
            @Valid @RequestBody ExpenseCreateDto dto, // DTO'yu al, valide et [1, 2]
            @AuthenticationPrincipal CustomUserDetails principal) { // Kullanıcı ID'sini token'dan al [3]

        // KRİTİK ADIM: Token'dan alınan kimlikten kullanıcı ID'sini çek
        Long userId = principal.getId();

        // Servisi çağır ve harcamayı oluştur
        ExpenseEntity expense = expenseService.createExpense(userId, dto);

        // Oluşturulan DTO'yu 201 Created status koduyla dön
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(expense));
    }

    // Diğer endpointler (GET, PUT, DELETE, SUMMARY) buraya gelecek [1]



    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getUserExpenses(
            @AuthenticationPrincipal CustomUserDetails principal
    ) {
        Long userId = principal.getId();

        List<ExpenseEntity> expenses = expenseService.getExpensesByUserId(userId);//id'nin yaptığı harcamaları bul

        // Entity → DTO çevir
        List<ExpenseDto> dtoList = expenses.stream()//her expenses nesnesini al dto ya çevir
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(dtoList);
    }




    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(
            @AuthenticationPrincipal CustomUserDetails principal,
            @PathVariable Long id,
            @Valid @RequestBody ExpenseCreateDto dto
    ) {

        Long userId = principal.getId();

        ExpenseEntity updated = expenseService.updateExpense(userId, id, dto);

        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(
            @AuthenticationPrincipal CustomUserDetails principal,
            @PathVariable Long id
    ){
        Long userId = principal.getId();//kullanıcının id'si
        expenseService.deleteExpenseById(id, userId);
    }

}