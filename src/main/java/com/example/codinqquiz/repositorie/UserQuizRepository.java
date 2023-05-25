package com.example.codinqquiz.repositorie;

import com.example.codinqquiz.model.Quiz;
import com.example.codinqquiz.model.User;
import com.example.codinqquiz.model.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuizRepository extends JpaRepository<UserQuiz, Integer> {
    List<UserQuiz> findAllByUser(User user);

}
