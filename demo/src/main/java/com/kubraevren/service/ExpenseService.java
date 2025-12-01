package com.kubraevren.service;

import com.kubraevren.dto.ExpenseCreateDto;
import com.kubraevren.mapper.ExpenseMapper;
import com.kubraevren.model.ExpenseEntity;
import com.kubraevren.model.UserEntity;
import com.kubraevren.repository.ExpenseRepository;
import com.kubraevren.repository.UserRepository; // Eksikti, eklendi
import com.kubraevren.wrappper.BaseException;
import com.kubraevren.wrappper.ResponseMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
    private final UserRepository userRepository;



    public ExpenseEntity createExpense(Long userId, ExpenseCreateDto dto) {
        ExpenseEntity expense = expenseMapper.toEntity(dto);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ResponseMessage.USER_NOT_FOUND));
        expense.setUser(user);
        return expenseRepository.save(expense);
    }



    public List<ExpenseEntity> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId);
    }


    public ExpenseEntity updateExpense(Long userId, Long expenseId, ExpenseCreateDto dto) {

        // 1) Güncellenecek kaydı bul
        ExpenseEntity expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new BaseException(ResponseMessage.EXPENSE_NOT_FOUND));

        // 2) Bu kayıt bu kullanıcıya mı ait?
        if (!expense.getUser().getId().equals(userId)) {
            throw new BaseException(ResponseMessage.UNAUTHORIZED_USER);
        }

        // 3) Yeni değerleri set et (body’den gelen dto)
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setTransactionType(dto.getTransactionType());
        expense.setDescription(dto.getDescription());
        expense.setDate(dto.getDate());

        // 4) Kaydet
        return expenseRepository.save(expense);
    }

    public void deleteExpenseById(Long expenseId, Long userId) {
        ExpenseEntity expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new BaseException(ResponseMessage.EXPENSE_NOT_FOUND));

        if (!expense.getUser().getId().equals(userId)) {
            throw new BaseException(ResponseMessage.UNAUTHORIZED_USER);        }

        expenseRepository.delete(expense);
    }
}