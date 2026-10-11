package org.example.expensetracker.dto.expense;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateExpenseRequest (
        @NotBlank
        String title,
        @NotNull
        @Positive
        BigDecimal amount,
        @NotNull
        LocalDate date,
        @NotNull
        Long categoryId
) {
}
