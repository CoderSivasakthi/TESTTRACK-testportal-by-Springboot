package com.example.TestApp.DAO;

import com.example.TestApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Long> {
    List<Question> findByCategory(String category);
    List<Question> findByDifficultyLevel(String difficultyLevel);
    List<Question> findByCategoryAndDifficultyLevel(String category, String difficultyLevel);
}
