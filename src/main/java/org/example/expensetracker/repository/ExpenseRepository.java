package org.example.expensetracker.repository;

import org.example.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    @Query("""
            select e
            from Expense e
            where e.user.id = :userId
            and (:categoryId is NULL or e.category.id = :categoryId)
            and (:minAmount is Null or e.amount >= :minAmount)
            and (:startDate is null or e.date >= :startDate)
            and (:endDate is null or e.date <= :endDate)
""")
    List<Expense> findAllExpensesByUserId(
            @Param("userId") Long userId,
            @Param("categoryId") Long categoryId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("minAmount") BigDecimal minAmount
            );
}
