package org.example.expensetracker.exception;

public class UserNotFounException extends RuntimeException {
    public UserNotFounException(String email) {
        super("User with email " + email + " not found");
    }
}
