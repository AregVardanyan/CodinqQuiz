package com.example.codinqquiz.scheduler;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Data
@Component
public class QuizScheduler {

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletResponse response;

    private boolean quizIsRunning = false;
    private long startTime = 0L;

    @Scheduled(fixedDelay = 1000) // Run every 1 second
    public void checkQuizExpiration() throws IOException {
        if (quizIsRunning) {

            long timerDuration = 5000L;


            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;
            if (elapsedTime >= timerDuration) {
                quizIsRunning = false;
            }

        }
    }
}
