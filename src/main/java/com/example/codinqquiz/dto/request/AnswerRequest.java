package com.example.codinqquiz.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequest {
    private int id;
    private String answer;
    private String isCorrect;
    private int QuestionId;
}
