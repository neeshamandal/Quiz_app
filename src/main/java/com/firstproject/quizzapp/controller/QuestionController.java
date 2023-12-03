package com.firstproject.quizzapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.quizzapp.model.Question;
import com.firstproject.quizzapp.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController { 
	
	@Autowired
	QuestionService questionservice;
	
	@GetMapping("/allQuestion")
	public ResponseEntity<List<Question>> getAllQuestion() {
		return questionservice.getAllQuestion();
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity <List<Question>> getQuestionCatagory(@PathVariable String category){
		return questionservice.getQuestionCatagory(category);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionservice.addQuestion(question);
		
	}
	
	@DeleteMapping("/remove/{id}")
	public String deleteQuestion(@PathVariable Long id) {
		return questionservice.deleteQuestion(id);
	}
	
	@PutMapping("/update")
	public String updateQuestion(@RequestBody Question question) {
		return questionservice.updateQuestion(question);
	}
	
	
	

}
