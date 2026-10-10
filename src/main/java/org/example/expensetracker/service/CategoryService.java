package org.example.expensetracker.service;

import org.example.expensetracker.dto.category.CategoryResponse;
import org.example.expensetracker.dto.category.CreateCategoryRequest;
import org.example.expensetracker.exception.CategoryAlreadyExistexception;
import org.example.expensetracker.model.Category;
import org.example.expensetracker.model.User;
import org.example.expensetracker.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;
    UserService userService;

    public CategoryService(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }

    public CategoryResponse createCategory(CreateCategoryRequest request) {
        User user = userService.getCurrentUser();
        if(categoryRepository.existsByNameIgnoreCaseAndUserId(request.name(),  user.getId())) {
            throw new CategoryAlreadyExistexception(request.name());
        }
        Category category = new Category();
        category.setName(request.name());
        category.setUser(user);
        categoryRepository.save(category);
        return toResponse(category);
    }

    public List<CategoryResponse> getAllCategories() {
        User user = userService.getCurrentUser();
        return categoryRepository.findByUserId(user.getId()).stream().map(this::toResponse).toList();
    }
}
