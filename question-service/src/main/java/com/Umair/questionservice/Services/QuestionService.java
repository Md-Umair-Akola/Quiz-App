package com.Umair.questionservice.Services;

import com.Umair.questionservice.model.Question;
import com.Umair.questionservice.model.QuestionWrapper;
import com.Umair.questionservice.model.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    List<Question> getAllQuestion();

    Question createQuestion(Question question);

    Question getQuestionById(Long id);

    Question updateQuestionById(Question question,Long id);

    String deleteQuestionById(Long id);

    List<QuestionWrapper> getQuesByCategory(String category, Integer numOfQues);

    List<QuestionWrapper> getQuestionsByIds(List<Long> ids);

    Integer getScore(List<Response> response);
}
