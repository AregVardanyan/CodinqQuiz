package com.example.codinqquiz.service.impl;

import com.example.codinqquiz.event.UserRegisteredEvent;
import com.example.codinqquiz.model.User;
import com.example.codinqquiz.repositorie.RoleRepository;
import com.example.codinqquiz.repositorie.UserRepository;
import com.example.codinqquiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleInfo;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AppUtil appUtil;

    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public User register(User user) {
        if (userRepository.existsByEmailAndNickname(user.getEmail(), user.getNickname())) {
            return null;
        } else {

            user.setRoles(List.of(roleRepository.findByName("user").get()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setVerificationCode(appUtil.generateRandomIntsLen4());
            user = userRepository.save(user);
            applicationEventPublisher.publishEvent(new UserRegisteredEvent(this, user));
            return user;
        }
    }
    @Override
    public boolean verify(int userId, String verificationCode) {

        if (userRepository.existsByIdAndVerificationCode(userId, verificationCode)) {
            userRepository.updateVerificationCode(userId, null);
            return true;
        }return false;
    }

    @Override
    public User userById(int userId) {
        return userRepository.getReferenceById(userId);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User addUser(User user) {
        if(userRepository.existsByEmailAndNickname(user.getEmail(), user.getNickname())){
            return null;
        }
        else {
            String password = appUtil.generateRandomIntsLen4();
            user.setRoles(List.of(roleRepository.findByName("user").get()));
            user.setPassword(passwordEncoder.encode(password));
            user = userRepository.save(user);
            return user;
        }
    }
}
