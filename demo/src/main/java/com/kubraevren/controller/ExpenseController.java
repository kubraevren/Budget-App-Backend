package com.kubraevren.controller;
import com.kubraevren.dto.ExpenseCreateDto;
import com.kubraevren.dto.ExpenseDto;
import com.kubraevren.mapper.ExpenseMapper;
import com.kubraevren.security.CustomUserDetails;
import com.kubraevren.service.ExpenseService;
import com.kubraevren.model.ExpenseEntity;
import com.kubraevren.wrappper.ApiResponse;
import com.kubraevren.wrappper.ResponseMessage;
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
    public ResponseEntity<ApiResponse<ExpenseDto>> createExpense(//veri ekleme HARCAMAA EKLE
                                                                 @Valid @RequestBody ExpenseCreateDto dto,
                                                                 @AuthenticationPrincipal CustomUserDetails principal) {
        Long userId = principal.getId();
        ExpenseEntity expense = expenseService.createExpense(userId, dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(ResponseMessage.EXPENSE_CREATED, mapper.toDto(expense)));
    }

    // Diğer endpointler (GET, PUT, DELETE, SUMMARY) buraya gelecek [1]



    @GetMapping//VERİ OKUMA - listeleme
    public ResponseEntity<ApiResponse<List<ExpenseDto>>> getUserExpenses(
            @AuthenticationPrincipal CustomUserDetails principal
    ) {
        Long userId = principal.getId();

        List<ExpenseEntity> expenses = expenseService.getExpensesByUserId(userId);//id'nin yaptığı harcamaları bul

        List<ExpenseDto> dtoList = mapper.toDtoList(expenses);//her expenses nesnesini al dto ya çevir


        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS,dtoList));
    }




    @PutMapping("/{id}") //veri güncelleme
    public ResponseEntity<ApiResponse<ExpenseDto>> updateExpense(
            @AuthenticationPrincipal CustomUserDetails principal,
            @PathVariable Long id,
            @Valid @RequestBody ExpenseCreateDto dto
    ) {

        Long userId = principal.getId();

        ExpenseEntity updated = expenseService.updateExpense(userId, id, dto);

        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.EXPENSE_UPDATED,mapper.toDto(updated)));
    }

    @DeleteMapping("/{id}")//veri silme
    public ResponseEntity<ApiResponse<Void>> deleteExpense(
            @AuthenticationPrincipal CustomUserDetails principal,
            @PathVariable Long id
    ){
        Long userId = principal.getId();//kullanıcının id'si
        expenseService.deleteExpenseById(id, userId);

        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.EXPENSE_DELETED, null));
    }

    //findAllByUserId(...)
    //findAllByUserIdAndDateBetween(...)
    //findAllByUserIdAndCategory(...)

}