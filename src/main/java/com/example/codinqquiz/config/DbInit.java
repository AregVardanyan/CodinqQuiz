package com.example.codinqquiz.config;

import com.example.codinqquiz.model.Category;
import com.example.codinqquiz.model.Role;
import com.example.codinqquiz.model.User;
import com.example.codinqquiz.repositorie.CategoryRepository;
import com.example.codinqquiz.repositorie.RoleRepository;
import com.example.codinqquiz.repositorie.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DbInit {


    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void initDb() {

        log.info("starting db initialization");
        if (roleRepository.findByName("admin").isEmpty()) {
            roleRepository.saveAll(Arrays.asList(new Role("admin"),
                    new Role("user")));
        }

        if(!userRepository.existsByEmailAndNickname("admin@gmail.com", "admin")){
            userRepository.save(User.builder()
                            .nickname("admin")
                            .email("admin@gmail.com")
                            .password(passwordEncoder.encode("123"))
                            .roles(Arrays.asList(roleRepository.findByName("admin").get()))
                    .build());
        }
        log.info("end db initialization");

    }
}
