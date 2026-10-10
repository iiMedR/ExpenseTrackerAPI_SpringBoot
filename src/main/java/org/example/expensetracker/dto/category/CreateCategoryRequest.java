package org.example.expensetracker.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest (
        @NotBlank
        @Size(max = 50)
        String name
) {
}
