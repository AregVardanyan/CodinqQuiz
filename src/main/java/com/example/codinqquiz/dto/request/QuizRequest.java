package com.example.codinqquiz.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizRequest {
    private int id;
    private String name;
    private String category;
    private String complexity;
}
