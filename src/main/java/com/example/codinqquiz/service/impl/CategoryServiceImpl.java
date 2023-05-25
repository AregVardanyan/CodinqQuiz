package com.example.codinqquiz.service.impl;

import com.example.codinqquiz.dto.request.CategoryRequest;
import com.example.codinqquiz.model.Category;
import com.example.codinqquiz.repositorie.CategoryRepository;
import com.example.codinqquiz.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(CategoryRequest categoryRequest){
        if(categoryRepository.existsByName(categoryRequest.getName())){
            return null;
        }
        else {
            return categoryRepository.save(Category.builder().name(categoryRequest.getName()).
                    build());
        }
    }

    @Override
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }
}
