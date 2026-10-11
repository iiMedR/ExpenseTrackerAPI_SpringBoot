package org.example.expensetracker.dto.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponse(
        Long id,
        String title,
        BigDecimal amount,
        LocalDate date,
        Long categoryId
) {
}
