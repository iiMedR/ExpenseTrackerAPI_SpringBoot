package org.example.expensetracker.exception;

public class CategoryAlreadyExistexception extends RuntimeException {
    public CategoryAlreadyExistexception(String name) {
        super("Category with name " + name + " already exists");
    }
}
