package org.example.expensetracker.controller;

import jakarta.validation.Valid;
import org.example.expensetracker.dto.expense.CreateExpenseRequest;
import org.example.expensetracker.dto.expense.ExpenseResponse;
import org.example.expensetracker.model.Expense;
import org.example.expensetracker.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> addExpense(@Valid @RequestBody CreateExpenseRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expenseService.createExpense(request));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> findAllExpenses() {
        List<ExpenseResponse> expenses = expenseService.findAllExpenses();
        return ResponseEntity.ok(expenses);
    }


}
