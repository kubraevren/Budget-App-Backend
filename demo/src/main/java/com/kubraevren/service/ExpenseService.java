package com.kubraevren.service;

import com.kubraevren.dto.ExpenseCreateDto;
import com.kubraevren.mapper.ExpenseMapper;
import com.kubraevren.model.ExpenseEntity;
import com.kubraevren.model.UserEntity;
import com.kubraevren.repository.ExpenseRepository;
import com.kubraevren.repository.UserRepository; // Eksikti, eklendi
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

        // 1. DTO -> Entity çevrimi
        ExpenseEntity expense = expenseMapper.toEntity(dto);

        // 2. Kullanıcıyı bul
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı"));

        // 3. İlişkiyi kur (Düzeltildi: UserEntity sınıfı değil, user nesnesi verildi)
        expense.setUser(user);

        // 4. Kaydet ve dön
        return expenseRepository.save(expense);
    }

    public List<ExpenseEntity> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId);
    }


    public ExpenseEntity updateExpense(Long userId, Long expenseId, ExpenseCreateDto dto) {

        // 1) Güncellenecek kaydı bul
        ExpenseEntity expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense bulunamadı"));

        // 2) Bu kayıt bu kullanıcıya mı ait?
        if (!expense.getUser().getId().equals(userId)) {
            throw new RuntimeException("Bu harcama bu kullanıcıya ait değil!");
        }

        // 3) Yeni değerleri set et (body’den gelen dto)
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setDescription(dto.getDescription());
        expense.setDate(dto.getDate());

        // 4) Kaydet
        return expenseRepository.save(expense);
    }

    public void deleteExpenseById(Long expenseId, Long userId) {
        ExpenseEntity expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Harcama bulunamadı"));

        if (!expense.getUser().getId().equals(userId)) {
            throw new RuntimeException("Bu harcamayı silmeye yetkiniz yok");
        }

        expenseRepository.delete(expense);
    }
}