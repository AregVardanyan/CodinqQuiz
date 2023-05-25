package com.example.codinqquiz.service;

import com.example.codinqquiz.dto.request.QuestionRequest;
import com.example.codinqquiz.dto.response.QuestionResponse;
import com.example.codinqquiz.model.Question;

public interface QuestionService {
    Question addQuestion(Question question);

    QuestionResponse getQuestionsByQuizId(int quizId);

    Question addQuestion(QuestionRequest questionRequest);

    Question findById(int questionId);

    Question updateQuestion(Question question, QuestionRequest questionRequest);
}
