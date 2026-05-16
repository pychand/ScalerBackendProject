package com.scalers.productserviceebatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.scalers.productserviceebatch.models.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //    Category findById(long id);
    Optional<Category> findByTitle(String title);
}