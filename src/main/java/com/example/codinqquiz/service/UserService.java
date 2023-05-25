package com.example.codinqquiz.service;

import com.example.codinqquiz.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface UserService {
    User register(User user);

    boolean verify(int userId, String verificationCode);

    User userById(int userId);

    void delete(int id);

    User addUser(User user);

}

