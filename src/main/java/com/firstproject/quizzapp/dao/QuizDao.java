package com.firstproject.quizzapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstproject.quizzapp.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Long> {

}
