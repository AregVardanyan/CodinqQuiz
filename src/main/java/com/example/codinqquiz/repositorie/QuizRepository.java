package com.example.codinqquiz.repositorie;

import com.example.codinqquiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    boolean existsByNameAndCategoryName(String name, String categoryName);

    List<Quiz> getQuizzesByCategoryId(int categoryId);
}
