package com.example.codinqquiz.controller;

import com.example.codinqquiz.dto.request.VerificationCode;
import com.example.codinqquiz.model.Quiz;
import com.example.codinqquiz.model.User;
import com.example.codinqquiz.scheduler.QuizScheduler;
import com.example.codinqquiz.security.CurrentUser;
import com.example.codinqquiz.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Timer;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final HttpSession session;
    private final UserService userService;
    private final QuizService quizService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final CategoryService categoryService;
    private final QuizScheduler quizScheduler;

    @PostMapping("")
    public String register(@ModelAttribute User user, Model model) {
        User savedUser = userService.register(user);

        return "redirect:/user/verify_page?user_id="+user.getId();
    }

    @GetMapping ("/verify_page")
    public String verifyPage(@RequestParam(name = "user_id") int userId, Model model){
        model.addAttribute("user_id", userId);
        model.addAttribute("verification_code",new VerificationCode());
        return "verify";
    }

    @PostMapping ("/verify")
    public String verify(@RequestParam(name = "user_id") int userId,
                         @ModelAttribute VerificationCode verificationCode,
                         Model model){
        boolean isVerified = userService.verify(userId, verificationCode.getCode());
        if (isVerified) {
            User currentUser = userService.userById(userId);
            model.addAttribute("currentUser", currentUser);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(currentUser.getEmail(), currentUser.getPassword()));
            return "redirect:/user/home";
        } else {
            return "verifyError";
        }
    }
    @GetMapping("/home")
    public String userHome(@AuthenticationPrincipal CurrentUser currentUser,
                           Model model) {
        if (currentUser.getUser().isAdmin()) {
            return "redirect:/admin/home";
        }
        model.addAttribute("quizzes", quizService.getAllQuizzes().getQuizzes());

        return "userHome";
    }

    @GetMapping("/pass-quiz")
    private String passQuiz(Model model,
                            @ModelAttribute(name = "quizId") int quizId){
        Quiz quiz = quizService.findById(quizId);
        model.addAttribute("quiz", quiz);
        return "passQuiz";
    }

    @GetMapping("/question-block")
    private String questionBlock(Model model,
                            @ModelAttribute(name = "quizId") int quizId,
                                 @ModelAttribute(name = "questionIndex") int questionIndex,
                                 @ModelAttribute(name = "correctAnswers") int correctAnswers){
        if(questionIndex == 0){
            quizScheduler.setStartTime(System.currentTimeMillis());
            quizScheduler.setQuizIsRunning(true);
        }
        Quiz quiz = quizService.findById(quizId);
        if(questionIndex== quiz.getQuestions().size() || !quizScheduler.isQuizIsRunning()){
            model.addAttribute("quiz", quiz);
            model.addAttribute("correctAnswers",correctAnswers);
            return "inner/quizFinish";
        }
        model.addAttribute("quiz", quiz);
        model.addAttribute("question", quiz.getQuestions().get(questionIndex));
        model.addAttribute("idx", questionIndex);

        return "inner/questionBlockInner";
    }


}
