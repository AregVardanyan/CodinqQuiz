package com.example.codinqquiz.repositorie;

import com.example.codinqquiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    boolean existsByQuestionAndQuizId(String question, int quizId);

    List<Question> getQuestionsByQuizId(int quizId);
}
