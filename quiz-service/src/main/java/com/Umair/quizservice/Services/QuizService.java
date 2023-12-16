package com.Umair.quizservice.Services;

import com.Umair.quizservice.Model.QuestionWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {

    String generateQuiz(String category, Integer numOfQues, String title);

    List<QuestionWrapper> getQuestionsOfQuiz(Long id);
}
