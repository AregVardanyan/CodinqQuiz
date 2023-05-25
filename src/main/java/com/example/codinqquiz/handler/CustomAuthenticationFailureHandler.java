package com.example.codinqquiz.handler;

import com.example.codinqquiz.repositorie.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final UserRepository userRepository;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception.getClass().isAssignableFrom(DisabledException.class)) {
            response.sendRedirect("/user/verify_page?user_id="+userRepository.findByEmail(request.getParameter("email")).get().getId());
        }
        if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) { //todo in front page
            response.sendRedirect("/?errorType=bad-credentials&msg=Bad credentials&email=" + request.getParameter("email") + "&password=" + request.getParameter("password"));
        }
        if (exception.getClass().isAssignableFrom(LockedException.class)) { //todo for students
            response.sendRedirect("/user/verify_page?user_id=");
        }
    }
}