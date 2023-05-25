package com.example.codinqquiz.service.impl;

import com.example.codinqquiz.dto.request.QuestionRequest;
import com.example.codinqquiz.dto.response.QuestionResponse;
import com.example.codinqquiz.model.Question;
import com.example.codinqquiz.model.Quiz;
import com.example.codinqquiz.model.enums.Complexity;
import com.example.codinqquiz.repositorie.QuestionRepository;
import com.example.codinqquiz.repositorie.QuizRepository;
import com.example.codinqquiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    @Override
    public Question addQuestion(Question question) {
        if (questionRepository.existsByQuestionAndQuizId(question.getQuestion(), question.getQuiz().getId())) {
            return null;
        } else {
            questionRepository.save(question);
            return question;
        }
    }

    @Override
    public QuestionResponse getQuestionsByQuizId(int quizId) {
        List<Question> content = questionRepository.getQuestionsByQuizId(quizId);
        return new QuestionResponse(content);
    }

    @Override
    public Question addQuestion(QuestionRequest questionRequest) {
        if(questionRepository.existsByQuestionAndQuizId(questionRequest.getQuestion(), questionRequest.getQuizId())){
            return null;
        }
        else{return questionRepository.save(Question.builder().id(questionRequest.getId())
                .question(questionRequest.getQuestion())
                .quiz(quizRepository.getReferenceById(questionRequest.getQuizId()))
                .build());}
    }

    @Override
    public Question findById(int questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    @Override
        public Question updateQuestion(Question question, QuestionRequest questionRequest) {


        question.setQuestion(questionRequest.getQuestion());
        return questionRepository.save(question);

    }
}
