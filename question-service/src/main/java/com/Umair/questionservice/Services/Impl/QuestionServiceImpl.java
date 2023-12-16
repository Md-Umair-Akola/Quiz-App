package com.Umair.questionservice.Services.Impl;

import com.Umair.questionservice.Repositories.QuestionRepo;
import com.Umair.questionservice.Services.QuestionService;
import com.Umair.questionservice.model.Question;
import com.Umair.questionservice.model.QuestionWrapper;
import com.Umair.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public List<Question> getAllQuestion() {
        return questionRepo.findAll();
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepo.findById(id).orElseThrow(()->new RuntimeException("Question is not found with id:"+id));
    }

    @Override
    public Question updateQuestionById(Question question, Long id) {
        if(questionRepo.existsById(id)){
            Question que = questionRepo.findById(id).get();
            que.setQuestion(question.getQuestion());
            que.setOption1(question.getOption1());
            que.setOption2(question.getOption2());
            que.setOption3(question.getOption3());
            que.setOption4(question.getOption4());
            que.setAnswer(question.getAnswer());
            que.setCategory(question.getCategory());
            que.setDifficulty(question.getDifficulty());
            questionRepo.save(que);
            return que;
        }
        else throw new RuntimeException("Question is not found with id:"+id);
    }

    @Override
    public String deleteQuestionById(Long id) {
         questionRepo.deleteById(id);
         return "Deleted";
    }

    @Override
    public List<QuestionWrapper> getQuesByCategory(String category, Integer numOfQues){
        List<Question> quesByCategoryAndLimit = questionRepo.getQuesByCategoryAndLimit(category, numOfQues);
        List<QuestionWrapper> wrappers = quesByCategoryAndLimit.stream().map(ques -> {
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(ques.getId());
            questionWrapper.setQuestion(ques.getQuestion());
            questionWrapper.setOption1(ques.getOption1());
            questionWrapper.setOption2(ques.getOption2());
            questionWrapper.setOption3(ques.getOption3());
            questionWrapper.setOption4(ques.getOption4());
            return questionWrapper;
        }).collect(Collectors.toList());
        return wrappers;
    }

    @Override
    public List<QuestionWrapper> getQuestionsByIds(List<Long> ids) {
        List<QuestionWrapper> wrappers=new ArrayList<>();
        for(Long id:ids){
            Question ques = questionRepo.findById(id).get();
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(ques.getId());
            questionWrapper.setQuestion(ques.getQuestion());
            questionWrapper.setOption1(ques.getOption1());
            questionWrapper.setOption2(ques.getOption2());
            questionWrapper.setOption3(ques.getOption3());
            questionWrapper.setOption4(ques.getOption4());
            wrappers.add(questionWrapper);
        }
        return wrappers;
    }

    @Override
    public Integer getScore(List<Response> response) {
        int right=0;
        for(Response res:response){
            Question ques = questionRepo.findById(res.getId()).get();
            if(res.getAnswer().equals(ques.getAnswer())){
                right++;
            }
        }
        return right;
    }
}
