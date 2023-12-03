package com.firstproject.quizzapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.firstproject.quizzapp.model.QuestionWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.firstproject.quizzapp.dao.QuestionDao;
import com.firstproject.quizzapp.dao.QuizDao;
import com.firstproject.quizzapp.model.Question;
import com.firstproject.quizzapp.model.Quiz;
import com.firstproject.quizzapp.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao quizdao;

	@Autowired
	QuestionDao questiondao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> question = questiondao.findRandomQuestionByCategory(category, numQ);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestion(question);
		quizdao.save(quiz);

		return new ResponseEntity<>("success", HttpStatus.CREATED);

	}

	public ResponseEntity<List<QuestionWrapper>> getQuestion(Long id) {
		Optional<Quiz> quiz = quizdao.findById(id);
		List<Question> questionFromDB = quiz.get().getQuestion();
		List<QuestionWrapper> questionForUser = new ArrayList<>();
		for(Question q : questionFromDB ) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionForUser.add(qw);
		}
		
		
		
		return new ResponseEntity<>(questionForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Long id, List<Response> responses) {
	Quiz quiz = quizdao.findById(id).get();	
	List<Question> questions = quiz.getQuestion();
	int right = 0;
	int i = 0;
	for(Response response : responses) {
		if(response.getResponse().equals(questions.get(i).getRightAnswer()))
			right++;
		
		i++;
	}
	return new ResponseEntity<>(right, HttpStatus.OK);
	}

}
