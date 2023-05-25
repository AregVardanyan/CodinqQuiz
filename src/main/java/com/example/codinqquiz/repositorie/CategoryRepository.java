package com.example.codinqquiz.repositorie;

import com.example.codinqquiz.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);

    Category findByName(String name);
}
