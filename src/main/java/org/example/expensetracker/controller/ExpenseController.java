package org.example.expensetracker.controller;

import jakarta.validation.Valid;
import org.example.expensetracker.dto.expense.CreateExpenseRequest;
import org.example.expensetracker.dto.expense.UpdateExpenseRequest;
import org.example.expensetracker.dto.expense.ExpenseResponse;
import org.example.expensetracker.dto.expense.TotalExpensesResponse;
import org.example.expensetracker.model.Expense;
import org.example.expensetracker.service.ExpenseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public ResponseEntity<List<ExpenseResponse>> findAllExpenses(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, @RequestParam(required = false) BigDecimal minAmount) {
        List<ExpenseResponse> expenses = expenseService.findAllExpenses(categoryId, startDate, endDate, minAmount);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/total")
    public ResponseEntity<TotalExpensesResponse> findAllTotalExpenses(@RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(expenseService.findTotalExpenses(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> findExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.findExpenseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody UpdateExpenseRequest request) {
        return ResponseEntity.ok(expenseService.updateExpense(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }


}
