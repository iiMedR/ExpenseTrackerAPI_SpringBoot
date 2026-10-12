package org.example.expensetracker.dto.expense;

import java.math.BigDecimal;

public record TotalExpensesResponse(
        BigDecimal total
) {
}
