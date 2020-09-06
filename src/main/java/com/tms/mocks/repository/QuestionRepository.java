package com.tms.mocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.mocks.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	List<Question> findByChapterId(final Long chapterId);

}
