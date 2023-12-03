package com.firstproject.quizzapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.firstproject.quizzapp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Long>{

	List<Question> findByCategory(String category);

	@Query(value = "SELECT * FROM question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
	List<Question> findRandomQuestionByCategory(String category, int numQ);

}
