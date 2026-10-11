package org.example.expensetracker.repository;

import org.example.expensetracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByIdAndUserId(long id, Long userId);
    List<Category> findByUserId(Long userId);
    boolean existsByNameIgnoreCaseAndUserId(String name,Long userId);
}
