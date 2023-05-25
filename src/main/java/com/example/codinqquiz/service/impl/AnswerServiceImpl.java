package com.example.codinqquiz.service.impl;

import com.example.codinqquiz.dto.request.AnswerRequest;
import com.example.codinqquiz.dto.response.AnswerResponse;
import com.example.codinqquiz.model.Answer;
import com.example.codinqquiz.repositorie.AnswerRepository;
import com.example.codinqquiz.repositorie.QuestionRepository;
import com.example.codinqquiz.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;


    @Override
    public Answer findAnswerById(int id) {
        return answerRepository.findById(id).orElse(null);
    }

    @Override
    public Answer addAnswer(AnswerRequest answerRequest) {
        if (answerRepository.existsByAnswerAndQuestionId(answerRequest.getAnswer(), answerRequest.getQuestionId())) {
            return null;
        } else {
            return answerRepository.save(Answer.builder()
                    .answer(answerRequest.getAnswer())
                    .isCorrect(Boolean.parseBoolean(answerRequest.getIsCorrect()))
                    .question(questionRepository.getReferenceById(answerRequest.getQuestionId())).build());

        }
    }

    @Override
    public AnswerResponse getAnswersByQuestionId(int questionId) {
        List<Answer> content = answerRepository.getAnswersByQuestionId(questionId);
        return new AnswerResponse(content);
    }

    @Override
    public Answer findById(int id) {
        return answerRepository.findById(id).orElse(null);
    }

    @Override
    public Answer updateAnswer(Answer answer, AnswerRequest answerRequest) {

        answer.setAnswer(answerRequest.getAnswer());
        answer.setIsCorrect(Boolean.parseBoolean(answerRequest.getIsCorrect()));
        return answerRepository.save(answer);
    }
}
