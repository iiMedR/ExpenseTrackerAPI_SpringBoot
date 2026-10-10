package org.example.expensetracker.dto.user;

public record UserResponse(
        Long id,
        String username,
        String email
) {
}
