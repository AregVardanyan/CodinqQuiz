package com.example.codinqquiz.service;

import com.example.codinqquiz.dto.request.QuizRequest;
import com.example.codinqquiz.dto.response.QuizResponse;
import com.example.codinqquiz.model.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface QuizService {

    Quiz findById(int id);
    Quiz addQuiz(QuizRequest quizRequest);
    QuizResponse getAllQuizzes();

    QuizResponse getQuizzesByCategoryId(int categoryId);

    Quiz updateQuiz(Quiz quiz, QuizRequest quizRequest);
}
