package com.example.codinqquiz.service.impl;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AppUtil {
    public String generateRandomIntsLen4() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4  ;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
