package com.example.codinqquiz.controller;

import com.example.codinqquiz.dto.request.AnswerRequest;
import com.example.codinqquiz.dto.request.CategoryRequest;
import com.example.codinqquiz.dto.request.QuestionRequest;
import com.example.codinqquiz.dto.request.QuizRequest;
import com.example.codinqquiz.model.Answer;
import com.example.codinqquiz.model.Question;
import com.example.codinqquiz.model.Quiz;
import com.example.codinqquiz.model.enums.Complexity;
import com.example.codinqquiz.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final QuizService quizService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final CategoryService categoryService;

    @GetMapping("/home")
    public String adminHome(Model model){
        model.addAttribute("quiz", new Quiz());
        model.addAttribute("complexities", Complexity.values());
        model.addAttribute("categories", categoryService.allCategories());
        model.addAttribute("quizzes",quizService.getAllQuizzes().getQuizzes());
        model.addAttribute("categories", categoryService.allCategories());
        return "adminHome";
    }

    @PostMapping("/quiz")
    public ResponseEntity<String> addQuiz(@RequestBody QuizRequest quizRequest) {
        quizService.addQuiz(quizRequest);
        return ResponseEntity.ok().body("Succesfuly created");
    }

    @PostMapping("/category")
    public ResponseEntity<String> addCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.addCategory(categoryRequest);
        return ResponseEntity.ok().body("Succesfuly created");
    }

    @GetMapping("/quiz-table")
    public String quizTable(Model model){
            model.addAttribute("quizzes",quizService.getAllQuizzes().getQuizzes());
            return "inner/quizzes-block";

    }

    @GetMapping("/quiz")
    public String quizEdit(@RequestParam(name = "quizId" ) int quizId,
            Model model){
        model.addAttribute("quiz", quizService.findById(quizId));
        model.addAttribute("complexities", Complexity.values());
        model.addAttribute("categories", categoryService.allCategories());
        model.addAttribute("question", new Question());
        model.addAttribute("questions",questionService.getQuestionsByQuizId(quizId).getQuestions());
        return "quizEdit";
    }

    @PutMapping("/quiz")
    public ResponseEntity<String> updateQuiz(@RequestBody QuizRequest quizRequest) {
        quizService.updateQuiz(quizService.findById(quizRequest.getId()),quizRequest);
        return ResponseEntity.ok().body("successfully added");
    }


    @PostMapping("/question")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionRequest questionRequest) {
        Question question = questionService.addQuestion(questionRequest);
        return ResponseEntity.ok().body("successfully added");
    }

    @GetMapping("/questions-table")
    public String questionTable(Model model,
                                @RequestParam(name = "quizId") int quizId){
        model.addAttribute("questions",questionService.getQuestionsByQuizId(quizId).getQuestions());
        return "inner/questionsBlock";
    }

    @GetMapping("/question")
    public String questionEdit(@RequestParam(name = "questionId" ) int questionId,
                           Model model){
        model.addAttribute("question", questionService.findById(questionId));
        model.addAttribute("answer", new Answer());
        model.addAttribute("answers",answerService.getAnswersByQuestionId(questionId).getAnswers());
        return "questionEdit";
    }
    @PutMapping("/question")
    public ResponseEntity<String> updateQuestion(@RequestBody QuestionRequest questionRequest) {
        questionService.updateQuestion(questionService.findById(questionRequest.getId()),questionRequest);
        return ResponseEntity.ok().body("successfully added");
    }

    @PostMapping("/answer")
    public ResponseEntity<String> addAnswer(@RequestBody AnswerRequest answerRequest) {
        Answer answer = answerService.addAnswer(answerRequest);
        return ResponseEntity.ok().body("successfully added");
    }

    @GetMapping("/answers-table")
    public String answerTable(Model model,
                                @RequestParam(name = "questionId") int questionId){
        model.addAttribute("answers",answerService.getAnswersByQuestionId(questionId).getAnswers());
        return "inner/answersBlock";
    }

    @GetMapping("/answer")
    public String answerEdit(@RequestParam(name = "answerId" ) int answerId,
                               Model model){
        model.addAttribute("answer", answerService.findAnswerById(answerId));

        return "answerEdit";
    }

    @PutMapping("/answer")
    public ResponseEntity<String> updateAnswer(@RequestBody AnswerRequest answerRequest) {
        answerService.updateAnswer(answerService.findById(answerRequest.getId()),answerRequest);
        return ResponseEntity.ok().body("successfully added");
    }


}
