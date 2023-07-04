package com.casestudymodule6.controller;

import com.casestudymodule6.model.question.Question;
import com.casestudymodule6.model.question.SearchQuestion;
import com.casestudymodule6.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/puzzling/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @PostMapping ("/search")
    public ResponseEntity<List<Question>> searchQuestions(@RequestBody SearchQuestion searchQuestion) {
        List<Question> questions = questionService.searchAllFields(searchQuestion);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

}
