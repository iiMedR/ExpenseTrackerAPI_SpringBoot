package org.example.expensetracker.service;

import org.example.expensetracker.dto.expense.CreateExpenseRequest;
import org.example.expensetracker.dto.expense.ExpenseResponse;
import org.example.expensetracker.exception.CategoryNotFoundException;
import org.example.expensetracker.model.Category;
import org.example.expensetracker.model.Expense;
import org.example.expensetracker.model.User;
import org.example.expensetracker.repository.CategoryRepository;
import org.example.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserService userService, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    private ExpenseResponse toResponse(Expense expense) {
        return new  ExpenseResponse(expense.getId(), expense.getTitle(), expense.getAmount(), expense.getDate(), expense.getCategory().getId());
    }

    public ExpenseResponse createExpense(CreateExpenseRequest request) {
        User user = userService.getCurrentUser();
        Expense expense = new Expense();
        expense.setTitle(request.title());
        expense.setAmount(request.amount());
        expense.setDate(request.date());
        Category category = categoryRepository.findByIdAndUserId(request.categoryId(), user.getId()).orElseThrow(() -> new CategoryNotFoundException(request.categoryId()));
        expense.setCategory(category);
        expense.setUser(user);

        expenseRepository.save(expense);
        return toResponse(expense);
    }
}
