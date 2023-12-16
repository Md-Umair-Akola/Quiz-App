package com.Umair.quizservice.Feign;


import com.Umair.quizservice.Model.QuestionWrapper;
import com.Umair.quizservice.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/get")
    public ResponseEntity<List<QuestionWrapper>> getQuestionByCategory(@RequestParam String category, @RequestParam Integer numOfQues);

    @PostMapping("question/getByIds")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(@RequestBody List<Long> ids);

    @GetMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
