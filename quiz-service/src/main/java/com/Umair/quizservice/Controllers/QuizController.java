package com.Umair.quizservice.Controllers;

import com.Umair.quizservice.Model.QuestionWrapper;
import com.Umair.quizservice.Model.QuizDto;
import com.Umair.quizservice.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/generate")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return new ResponseEntity<>(quizService.generateQuiz(quizDto.getCategory(),quizDto.getNumberOfQues(),quizDto.getTitle()), HttpStatus.CREATED);
    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionOfQuiz(@PathVariable("quizId") Long id){
        return new ResponseEntity<>(quizService.getQuestionsOfQuiz(id),HttpStatus.OK);
    }
}
