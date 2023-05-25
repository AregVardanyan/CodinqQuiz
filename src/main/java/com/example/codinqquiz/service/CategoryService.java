package com.example.codinqquiz.service;

import com.example.codinqquiz.dto.request.CategoryRequest;
import com.example.codinqquiz.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    Category addCategory(CategoryRequest categoryRequest);

    List<Category> allCategories();
}
