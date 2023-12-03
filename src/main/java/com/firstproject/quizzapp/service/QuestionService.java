package com.firstproject.quizzapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.firstproject.quizzapp.dao.QuestionDao;
import com.firstproject.quizzapp.model.Question;

@Service
public class QuestionService {
	@Autowired
	private QuestionDao questiondao;
	
	public ResponseEntity<List<Question>>  getAllQuestion(){
		try {
		return new ResponseEntity<> (questiondao.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return new ResponseEntity<> (new ArrayList(), HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<List<Question>> getQuestionCatagory(String category) {
		// TODO Auto-generated method stub
		try {
		return new ResponseEntity<>(questiondao.findByCategory(category), HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(new ArrayList(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questiondao.save(question);
		try {
		return new ResponseEntity<>("success", HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("unsucessful", HttpStatus.BAD_REQUEST);
		
	}

	public String deleteQuestion(Long id) {
		questiondao.deleteById(id);
		return "success";
	}

	public String updateQuestion(Question question) {
		questiondao.save(question);
		return "success";
	}

}
