package com.example.codinqquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CodinqQuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodinqQuizApplication.class, args);
    }

}
