package com.example.codinqquiz.dto.response;

import com.example.codinqquiz.model.UserQuiz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor
public class UserQuizResponse {
    private List<UserQuiz> userQuizzes;
}
