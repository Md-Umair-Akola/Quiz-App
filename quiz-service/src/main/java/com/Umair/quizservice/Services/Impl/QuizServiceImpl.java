package com.Umair.quizservice.Services.Impl;

import com.Umair.quizservice.Feign.QuizInterface;
import com.Umair.quizservice.Model.QuestionWrapper;
import com.Umair.quizservice.Model.Quiz;
import com.Umair.quizservice.Repositories.QuizRepo;
import com.Umair.quizservice.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizInterface quizInterface;

    @Autowired
    private QuizRepo quizRepo;

    @Override
    public String generateQuiz(String category, Integer numOfQues, String title) {
        List<QuestionWrapper> questionByCategory = quizInterface.getQuestionByCategory(category, numOfQues).getBody();
        List<Long> quesIds=questionByCategory.stream().map(ques->{
            return ques.getId();
        }).collect(Collectors.toList());
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(quesIds);
        quizRepo.save(quiz);
        return "Success";
    }

    @Override
    public List<QuestionWrapper> getQuestionsOfQuiz(Long id) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Long> questionsIds = quiz.getQuestionsIds();
        List<QuestionWrapper> questionsByIds = quizInterface.getQuestionsByIds(questionsIds).getBody();
        return questionsByIds;
    }
}
