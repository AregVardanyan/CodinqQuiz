package com.example.codinqquiz.service;

import com.example.codinqquiz.dto.request.AnswerRequest;
import com.example.codinqquiz.dto.response.AnswerResponse;
import com.example.codinqquiz.model.Answer;

public interface AnswerService {

    Answer findAnswerById(int id);

    Answer addAnswer(AnswerRequest answerRequest);

    AnswerResponse getAnswersByQuestionId(int questionId);

    Answer findById(int id);

    Answer updateAnswer(Answer answer, AnswerRequest answerRequest);
}
