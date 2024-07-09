package com.example.TestApp.service;

import com.example.TestApp.DAO.QuestionDAO;
import com.example.TestApp.model.Question;
import com.example.TestApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionDAO questionDAO;

    public List<Question> getAllQuestions() {
        return questionDAO.findAll();
    }

    public List<String> getAllCategories() {
        return questionDAO.findAll().stream().map(Question::getCategory).distinct().collect(Collectors.toList());
    }

    public List<String> getAllDifficultyLevels() {
        return questionDAO.findAll().stream().map(Question::getDifficultyLevel).distinct().collect(Collectors.toList());
    }

    public Optional<Question> getQuestionById(Long questionId) {
        return questionDAO.findById(questionId);
    }

    public int calcResult(List<Response> responses) {
        List<Question> questions = questionDAO.findAll();
        int correctAnswers = 0;

        for (int i = 0; i < responses.size(); i++) {
            Response response = responses.get(i);
            Question question = questions.get(i);
            if (response.getResponse().equals(question.getRightAnswer())) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

    public List<Question> getQuestionsByFilters(String category, String difficulty) {
        if (category.equals("all") && difficulty.equals("all")) {
            return questionDAO.findAll();
        } else if (category.equals("all")) {
            return questionDAO.findByDifficultyLevel(difficulty);
        } else if (difficulty.equals("all")) {
            return questionDAO.findByCategory(category);
        } else {
            return questionDAO.findByCategoryAndDifficultyLevel(category, difficulty);
        }
    }
}
