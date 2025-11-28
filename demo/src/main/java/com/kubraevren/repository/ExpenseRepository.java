package com.kubraevren.repository;

import com.kubraevren.model.ExpenseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    // SQL: select * from expenses where user_id = ?
    Page<ExpenseEntity> findByUserId(Long userId, Pageable pageable);

    // 2. Tarih aralığına göre filtrele (Örn: Bu ayın harcamaları)
    // SQL: select * from expenses where user_id = ? and date between ? and ?
    List<ExpenseEntity> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    // 3. Kategoriye göre filtrele (Opsiyonel, ileride lazım olur)
    List<ExpenseEntity> findByUserIdAndCategory(Long userId, String category);

    @Query("select sum(e.amount) from ExpenseEntity e where e.user.id = :userId and e.date between :start and :end")
    BigDecimal sumAmountBetween(@Param("userId") Long userId, @Param("start") LocalDate start, @Param("end") LocalDate end);


    List<ExpenseEntity> findByUserId(Long userId);


    Optional<ExpenseEntity> findById(Long id);

}






