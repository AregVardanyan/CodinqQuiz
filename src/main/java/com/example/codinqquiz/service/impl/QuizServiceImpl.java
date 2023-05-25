package com.example.codinqquiz.service.impl;

import com.example.codinqquiz.dto.request.QuizRequest;
import com.example.codinqquiz.dto.response.QuizResponse;
import com.example.codinqquiz.model.Quiz;
import com.example.codinqquiz.model.enums.Complexity;
import com.example.codinqquiz.repositorie.CategoryRepository;
import com.example.codinqquiz.repositorie.QuizRepository;
import com.example.codinqquiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Quiz findById(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        return quiz.orElse(null);
    }

    @Override
    public Quiz addQuiz(QuizRequest quizRequest) {
        if(quizRepository.existsByNameAndCategoryName(quizRequest.getName(), quizRequest.getCategory())){
            return null;
        }
        else{return quizRepository.save(Quiz.builder().id(quizRequest.getId())
                .name(quizRequest.getName())
                .category(categoryRepository.findByName(quizRequest.getCategory()))
                .complexity(Complexity.valueOf(quizRequest.getComplexity()))
                .build());}

    }

    @Override
    public QuizResponse getAllQuizzes() {
        List<Quiz> content = quizRepository.findAll();
        return new QuizResponse(content);
    }

    @Override
    public QuizResponse getQuizzesByCategoryId(int categoryId) {
        List<Quiz> content= quizRepository.getQuizzesByCategoryId(categoryId);
        return new QuizResponse(content);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz, QuizRequest quizRequest) {
        quiz.setName(quizRequest.getName());
        quiz.setCategory(categoryRepository.findByName(quizRequest.getCategory()));
        quiz.setComplexity(Complexity.valueOf(quizRequest.getComplexity()));
        return quizRepository.save(quiz);
    }
}
