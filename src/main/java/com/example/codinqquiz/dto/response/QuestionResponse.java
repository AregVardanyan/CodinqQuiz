package com.example.codinqquiz.dto.response;

import com.example.codinqquiz.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
    private List<Question> questions;
}
