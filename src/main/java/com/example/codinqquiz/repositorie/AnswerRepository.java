package com.example.codinqquiz.repositorie;

import com.example.codinqquiz.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    boolean existsByAnswerAndQuestionId(String answer, int questionId);

    List<Answer> getAnswersByQuestionId(int questionId);
}
