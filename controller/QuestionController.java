package com.example.TestApp.controller;

import com.example.TestApp.model.AnswerRequest;
import com.example.TestApp.model.Question;
import com.example.TestApp.model.Response;
import com.example.TestApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:1234")
@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/allquestions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return questionService.getAllCategories();
    }

    @GetMapping("/difficulties")
    public List<String> getDifficulties() {
        return questionService.getAllDifficultyLevels();
    }

    @GetMapping("/filter")
    public List<Question> getQuestionsByFilters(@RequestParam String category, @RequestParam String difficulty) {
        return questionService.getQuestionsByFilters(category, difficulty);
    }

    @PostMapping("/submit")
    public int submitAnswers(@RequestBody List<Response> responses) {
        return questionService.calcResult(responses);
    }

    @PostMapping("/checkAnswer")
    public boolean checkAnswer(@RequestBody AnswerRequest answerRequest) {
        Question question = questionService.getQuestionById(answerRequest.getQuestionId()).orElse(null);
        if (question == null) {
            return false;
        }
        return question.getRightAnswer().equals(answerRequest.getAnswer());
    }

    static class AnswerRequest {
        private Long questionId;
        private String answer;

        public Long getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Long questionId) {
            this.questionId = questionId;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
