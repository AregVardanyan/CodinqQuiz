package com.example.codinqquiz.dto.response;

import com.example.codinqquiz.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private List<User> users;
}
