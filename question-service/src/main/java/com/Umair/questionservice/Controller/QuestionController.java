package com.Umair.questionservice.Controller;

import com.Umair.questionservice.Services.QuestionService;
import com.Umair.questionservice.model.Question;
import com.Umair.questionservice.model.QuestionWrapper;
import com.Umair.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestion(), HttpStatus.OK);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable("questionId") Long id){
        return new ResponseEntity<>(questionService.getQuestionById(id),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
        return new ResponseEntity<>(questionService.createQuestion(question),HttpStatus.CREATED);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<Question> updateQuestionById(@RequestBody Question question,@PathVariable("questionId") Long id){
        return new ResponseEntity<>(questionService.updateQuestionById(question,id),HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteById(@PathVariable("questionId") Long id){
        return new ResponseEntity<>(questionService.deleteQuestionById(id),HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<QuestionWrapper>> getQuestionByCategory(@RequestParam String category,@RequestParam Integer numOfQues){
       return new ResponseEntity<>(questionService.getQuesByCategory(category,numOfQues),HttpStatus.OK);
    }

    @PostMapping("/getByIds")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(@RequestBody List<Long> ids){
        return new ResponseEntity<>(questionService.getQuestionsByIds(ids),HttpStatus.OK);
    }

    @GetMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return new ResponseEntity<>(questionService.getScore(responses),HttpStatus.OK);
    }
}
